package com.ssafy.housework.model.familyMemberInfo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FamilyMemberInfo {
    int id;
    int userId;
    int familyId;
    String name;
    String email;
    String profileImageUrl;
    String role;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
