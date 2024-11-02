package com.ssafy.housework.model.family.dto;

public class UpdateFamily {
    String name;
    String description;

    public UpdateFamily() {
    }

    public UpdateFamily(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
