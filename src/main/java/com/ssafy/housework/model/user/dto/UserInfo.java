package com.ssafy.housework.model.user.dto;

import java.util.List;

public record UserInfo(
        String email,
        String name,
        String profileImageName,
        int calorieGoal
) {

    public static UserInfo of(User user) {
        return new UserInfo(user.getEmail(), user.getName(), user.getProfileImageName(), user.getCalorieGoal());
    }

    public static List<UserInfo> of(List<User> users) {
        return users.stream().map(UserInfo::of).toList();
    }
}
