package com.ssafy.housework.model.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class User {
    int id;
    int familyId;
    String name;
    String email;
    String password;
    String profileImageUrl;
    Boolean isAdmin;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    // for sign up
    public User(int familyId, String name, String email, String password, String profileImageUrl) {
        this.familyId = familyId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.isAdmin = true;
    }
}