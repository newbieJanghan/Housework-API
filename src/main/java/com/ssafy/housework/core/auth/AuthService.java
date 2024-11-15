package com.ssafy.housework.core.auth;

import com.ssafy.housework.core.auth.dto.LoginDto;
import com.ssafy.housework.core.auth.token.AuthTokenHandler;
import com.ssafy.housework.core.auth.token.UserTokenInfo;
import com.ssafy.housework.model.user.UserDao;
import com.ssafy.housework.model.user.dto.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UserDao userDao;
    private final AuthTokenHandler tokenHandler;

    public AuthService(UserDao userDao, @Qualifier("jwt") AuthTokenHandler tokenHandler) {
        this.userDao = userDao;
        this.tokenHandler = tokenHandler;
    }

    public String login(LoginDto loginDto) {
        User user = userDao.selectByEmail(loginDto.email());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (user.getPassword().equals(loginDto.password())) {
            UserTokenInfo userTokenInfo = new UserTokenInfo(
                    user.getId(),
                    user.getFamilyId(),
                    user.getEmail(),
                    user.getIsAdmin()
            );

            return tokenHandler.generate(userTokenInfo);
        } else {
            throw new IllegalArgumentException("Password is not correct");
        }
    }

    public String extend(String token) {
        UserTokenInfo userTokenInfo = tokenHandler.parse(token);
        if (userTokenInfo == null) {
            throw new IllegalArgumentException("Invalid token");
        }

        return tokenHandler.generate(userTokenInfo);
    }
}
