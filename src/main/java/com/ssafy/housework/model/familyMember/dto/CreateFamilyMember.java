package com.ssafy.housework.model.familyMember.dto;

public class CreateFamilyMember {
    int userId;
    int familyId;
    String role;

    public CreateFamilyMember() {
    }

    public CreateFamilyMember(int userId, int familyId, String role) {
        this.userId = userId;
        this.familyId = familyId;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
