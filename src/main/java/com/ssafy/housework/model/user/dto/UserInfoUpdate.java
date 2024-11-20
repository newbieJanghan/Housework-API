package com.ssafy.housework.model.user.dto;

public record UserInfoUpdate(String name, String password, String profileImageName, Integer calorieGoal) {
    public void setUser(User user) {
        if (name() != null) user.setName(name());
        if (password() != null) user.setPassword(password());
        if (profileImageName() != null) user.setProfileImageName(profileImageName());
        if (calorieGoal() != null) user.setCalorieGoal(calorieGoal());
    }
}
