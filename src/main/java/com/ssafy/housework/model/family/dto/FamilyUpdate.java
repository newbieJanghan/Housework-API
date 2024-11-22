package com.ssafy.housework.model.family.dto;

public record FamilyUpdate(String name, String description) {
    public void setFamily(Family family) {
        if (this.name() != null) family.setName(this.name());
        if (this.description() != null) family.setDescription(this.description());
    }
}
