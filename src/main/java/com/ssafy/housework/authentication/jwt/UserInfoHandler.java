package com.ssafy.housework.authentication.jwt;

import com.ssafy.housework.authentication.dto.UserTokenInfo;
import com.ssafy.housework.model.user.dto.User;

public class UserInfoHandler {
    public static String makeUserInfoString(User user) {
        return user.getId() + " " + user.getEmail() + " " + user.getIsAdmin();
    }

    public static UserTokenInfo extractUserInfo(String info) {
        String[] infos = info.split(" ");
        return new UserTokenInfo(
                Integer.parseInt(infos[0]),
                infos[1],
                Boolean.valueOf(infos[2])
        );
    }
}
