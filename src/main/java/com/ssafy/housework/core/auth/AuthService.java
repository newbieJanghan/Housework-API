package com.ssafy.housework.core.auth;

import com.ssafy.housework.core.auth.dto.AuthenticatedUser;
import com.ssafy.housework.core.auth.dto.LoginRequest;
import com.ssafy.housework.core.auth.dto.TokenResponse;
import com.ssafy.housework.core.auth.token.AuthTokenHandler;
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

    public TokenResponse login(LoginRequest loginRequest) {
        User user = userDao.selectByEmail(loginRequest.email());
        if (user == null) {
            throw new IllegalArgumentException("User with this email is not found");
        }

        if (user.getPassword().equals(loginRequest.password())) {
            AuthenticatedUser authUser = new AuthenticatedUser(
                    user.getId(),
                    user.getFamilyId(),
                    user.getEmail(),
                    user.getIsAdmin()
            );

            return new TokenResponse(tokenHandler.generate(authUser));
        } else {
            throw new IllegalArgumentException("Password is not correct");
        }
    }

    public TokenResponse extend(String token) {
        AuthenticatedUser authUser = tokenHandler.parse(token);
        if (authUser == null) {
            throw new IllegalArgumentException("Invalid token");
        }

        return new TokenResponse(tokenHandler.generate(authUser));
    }
}
