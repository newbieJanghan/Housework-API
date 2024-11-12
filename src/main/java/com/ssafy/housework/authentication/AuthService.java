package com.ssafy.housework.authentication;

import com.ssafy.housework.authentication.dto.LoginDto;
import com.ssafy.housework.authentication.jwt.JWTUtil;
import com.ssafy.housework.authentication.jwt.UserInfoHandler;
import com.ssafy.housework.model.user.UserDao;
import com.ssafy.housework.model.user.dto.User;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String login(LoginDto loginDto) {
        User user = userDao.selectByEmail(loginDto.email());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (user.getPassword().equals(loginDto.password())) {
            return generateToken(user);
        } else {
            throw new IllegalArgumentException("Password is not correct");
        }
    }

    private String generateToken(User user) {
        return JWTUtil.generateToken(UserInfoHandler.makeUserInfoString(user));

    }
}
