package com.ssafy.housework.core.auth.service.dto;

import com.ssafy.housework.model.user.dto.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

public record SignupRequest(
        @Nullable Integer familyId,
        @NotNull String name,
        @NotNull String email,
        @NotNull String password,
        @Nullable String familyName,
        @Nullable String profileImageName,
        @Nullable Integer caloriesGoal
) {

    public String familyName() {
        return familyName == null ? name + "'s Family" : familyName;
    }

    public User toUser(int familyId) {
        return new User(this.familyId() == null ? familyId : this.familyId(), name, email, password, profileImageName, caloriesGoal);
    }

}
