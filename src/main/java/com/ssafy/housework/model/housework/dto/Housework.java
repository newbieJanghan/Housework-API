package com.ssafy.housework.model.housework.dto;

public class Housework {
    int id;
    String name;
    String category;

    public Housework() {
    }

    public Housework(String name, String category) {
        this.name = name;
        this.category = category;
    }

    // for mapper
    public Housework(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}