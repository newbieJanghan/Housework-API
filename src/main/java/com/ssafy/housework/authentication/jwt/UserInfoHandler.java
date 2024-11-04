package com.ssafy.housework.authentication.jwt;

import com.ssafy.housework.model.user.dto.User;

public class UserInfoHandler {
    public static String makeUserInfo(User user) {
        return user.getId() + " " + user.getEmail();
    }

    public static int extractUserId(String info) {
        return Integer.parseInt(info.split(" ")[0]);
    }
}
