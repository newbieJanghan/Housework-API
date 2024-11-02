package com.ssafy.housework.model.housework.dto;

public class UpdateHousework {
    String name;
    String category;

    public UpdateHousework() {
    }

    public UpdateHousework(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
