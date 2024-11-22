package com.ssafy.housework.model.housework.dto;

import java.time.LocalDateTime;

public record HouseworkUpdate(
        Integer assignedUserId,
        String name,
        String description,
        String color,
        Integer calorieAmount,
        LocalDateTime startAt,
        LocalDateTime dueAt
) {
    public HouseworkUpdate {
        if (calorieAmount != null && calorieAmount < 0) {
            throw new IllegalArgumentException("Calorie amount must be positive");
        }

        if (startAt != null && dueAt != null && startAt.isAfter(dueAt)) {
            throw new IllegalArgumentException("Start at must be before due at");
        }
    }

    public void setHousework(Housework housework) {
        if (this.assignedUserId() != null) housework.setAssignedUserId(this.assignedUserId());
        if (this.name() != null) housework.setName(this.name());
        if (this.description() != null) housework.setDescription(this.description());
        if (this.color() != null) housework.setColor(this.color());
        if (this.calorieAmount() != null) housework.setCalorieAmount(this.calorieAmount());
        if (this.startAt() != null) housework.setStartAt(this.startAt());
        if (this.dueAt() != null) housework.setDueAt(this.dueAt());
    }

    public void setHousework(int registerUserId, Housework housework) {
        housework.setRegisterUserId(registerUserId);
        setHousework(housework);
    }
}
