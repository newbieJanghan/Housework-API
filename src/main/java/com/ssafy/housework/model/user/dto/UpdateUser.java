package com.ssafy.housework.model.user.dto;

public class UpdateUser {
    String name;

    public UpdateUser() {
    }

    public UpdateUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
