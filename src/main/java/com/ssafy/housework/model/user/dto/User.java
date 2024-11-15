package com.ssafy.housework.model.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class User {
    int id;
    int familyId;
    String name;
    String email;
    String password;
    String familyRole;
    String profileImageName;
    int calorieGoal;
    Boolean isAdmin;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    // for sign up
    public User(int familyId, String name, String email, String password, String profileImageName, Integer calorieGoal) {
        this.familyId = familyId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImageName = profileImageName;
        this.calorieGoal = Objects.requireNonNullElse(calorieGoal, 0);
    }

    public UserInfo toUserInfo() {
        return new UserInfo(id, familyId, name, email, profileImageName, calorieGoal);
    }
}