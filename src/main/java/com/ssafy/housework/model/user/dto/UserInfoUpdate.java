package com.ssafy.housework.model.user.dto;

public record UserInfoUpdate(String name, String password, String profileImageName, Integer calorieGoal) {
    public void setUser(User user) {
        if (this.name() != null) user.setName(this.name());
        if (this.password() != null) user.setPassword(this.password());
        if (this.profileImageName() != null) user.setProfileImageName(this.profileImageName());
        if (this.calorieGoal() != null) user.setCalorieGoal(this.calorieGoal());
    }
}
