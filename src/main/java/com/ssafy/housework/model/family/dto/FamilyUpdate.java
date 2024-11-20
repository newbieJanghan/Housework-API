package com.ssafy.housework.model.family.dto;

public record FamilyUpdate(String name, String description) {
    public void setFamily(Family family) {
        if (name() != null) family.setName(name());
        if (description() != null) family.setDescription(description());
    }
}
