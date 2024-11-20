package com.ssafy.housework.model.user.dto;

public record UserInfo(
        int familyId,
        int id,
        String email,
        String name,
        String profileImageName,
        int calorieGoal
) {

    public static UserInfo fromUser(User user) {
        return new UserInfo(user.getFamilyId(), user.getId(), user.getEmail(), user.getName(), user.getProfileImageName(), user.getCalorieGoal());
    }
}
