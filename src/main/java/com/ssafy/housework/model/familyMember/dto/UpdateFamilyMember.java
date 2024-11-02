package com.ssafy.housework.model.familyMember.dto;

public class UpdateFamilyMember {
    String role;

    public UpdateFamilyMember() {
    }

    public UpdateFamilyMember(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
