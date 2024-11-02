package com.ssafy.housework.model.family.dto;

public class UpdateFamily {
    String name;
    String desc;

    public UpdateFamily() {
    }

    public UpdateFamily(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
