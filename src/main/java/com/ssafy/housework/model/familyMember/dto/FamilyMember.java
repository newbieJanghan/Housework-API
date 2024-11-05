package com.ssafy.housework.model.familyMember.dto;

public class FamilyMember {
    int id;
    int userId;
    int familyId;
    String role;

    public FamilyMember() {
    }

    public FamilyMember(int userId, int familyId, String role) {
        this.userId = userId;
        this.familyId = familyId;
        this.role = role;
    }

    // for mapper
    public FamilyMember(int id, int userId, int familyId, String role) {
        this.id = id;
        this.userId = userId;
        this.familyId = familyId;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
