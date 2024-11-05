package com.ssafy.housework.model.family.dto;

public class Family {
    int id;
    String name;
    String description;

    public Family() {
    }

    public Family(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // for mapper
    public Family(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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