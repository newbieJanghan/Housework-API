package com.ssafy.housework.model.user.dto;

public record UserInfoUpdate(String name, String password, String profileImageUrl, Integer calorieGoal) {
    public void setUser(User user) {
        if (name() != null) user.setName(name());
        if (password() != null) user.setPassword(password());
        if (profileImageUrl() != null) user.setProfileImageName(profileImageUrl());
        if (calorieGoal() != null) user.setCalorieGoal(calorieGoal());
    }
}
