package com.ssafy.housework.core.auth.service.dto;

import com.ssafy.housework.model.user.dto.User;

public record SignupRequest(
        Integer familyId,
        String name,
        String email,
        String password,
        String familyName,
        String profileImageName,
        Integer caloriesGoal
) {

    public String familyName() {
        return familyName == null ? name + "'s Family" : familyName;
    }

    public User toUser(int familyId) {
        return new User(this.familyId() == null ? familyId : this.familyId(), name, email, password, profileImageName, caloriesGoal);
    }

}
