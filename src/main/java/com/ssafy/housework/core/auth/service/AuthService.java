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
    private final FamilyDao familyDao;
    private final AuthTokenHandler tokenHandler;

    public AuthService(UserDao userDao, FamilyDao familyDao, @Qualifier("jwt") AuthTokenHandler tokenHandler) {
        this.userDao = userDao;
        this.familyDao = familyDao;
        this.tokenHandler = tokenHandler;
    }

    public TokenResponse signup(SignupRequest signupRequest) {
        User user = userDao.selectByEmail(signupRequest.email());
        if (user != null) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        Integer familyId = signupRequest.familyId();
        if (familyId == null) {
            Family family = new Family(signupRequest.familyName(), null);
            familyDao.insert(family);

            familyId = family.getId();
        }

        User newUser = signupRequest.toUser(familyId);
        userDao.insert(newUser);

        return new TokenResponse(tokenHandler.generate(AuthUser.of(newUser)));
    }

    public TokenResponse login(LoginRequest loginRequest) {
        User user = userDao.selectByEmail(loginRequest.email());
        if (user == null) {
            throw new IllegalArgumentException("User with this email is not found");
        }

        if (user.getPassword().equals(loginRequest.password())) {
            return new TokenResponse(tokenHandler.generate(AuthUser.of(user)));
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
