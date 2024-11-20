package com.ssafy.housework.core.auth.service;

import com.ssafy.housework.core.auth.service.dto.LoginRequest;
import com.ssafy.housework.core.auth.service.dto.SignupRequest;
import com.ssafy.housework.core.auth.service.dto.TokenResponse;
import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.token.AuthTokenHandler;
import com.ssafy.housework.model.family.FamilyDao;
import com.ssafy.housework.model.family.dto.Family;
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
            AuthUser authUser = new AuthUser(
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
        AuthUser authUser = tokenHandler.parse(token);
        if (authUser == null) {
            throw new IllegalArgumentException("Invalid token");
        }

        return new TokenResponse(tokenHandler.generate(authUser));
    }
}
