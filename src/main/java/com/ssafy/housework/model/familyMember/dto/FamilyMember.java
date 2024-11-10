package com.ssafy.housework.model.familyMember.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FamilyMember {
    int id;
    int userId;
    int familyId;
    String role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public FamilyMember(int userId, int familyId, String role) {
        this.userId = userId;
        this.familyId = familyId;
        this.role = role;
    }
}
