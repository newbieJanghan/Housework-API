package com.ssafy.housework.model.user.dto;

public class User {
    int id;
    int familyId;
    String name;
    String email;
    String password;
    String profileImageUrl;
    Boolean isAdmin;

    public User() {
    }

    // for sign up
    public User(int familyId, String name, String email, String password) {
        this.familyId = familyId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // for mapper
    public User(int id, int familyId, String name, String email, String password, String profileImageUrl, boolean isAdmin) {
        this.id = id;
        this.familyId = familyId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}