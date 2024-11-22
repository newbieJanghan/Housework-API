package com.ssafy.housework.model.housework.dto;

import java.time.LocalDateTime;

public record HouseworkCreate(
        int assignedUserId,
        String name,
        String description,
        String color,
        int calorieAmount,
        LocalDateTime startAt,
        LocalDateTime dueAt
) {
    public HouseworkCreate {
        if (assignedUserId <= 0) {
            throw new IllegalArgumentException("Assigned user id must be positive");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }

        if (calorieAmount < 0) {
            throw new IllegalArgumentException("Calorie amount must be positive");
        }

        if (startAt == null || dueAt == null) {
            throw new IllegalArgumentException("Start at and due at must not be null");
        }

        if (startAt.isAfter(dueAt)) {
            throw new IllegalArgumentException("Start at must be before due at");
        }
    }

    public Housework toHousework(int familyId, int registerUserId) {
        return new Housework(
                familyId,
                registerUserId,
                this.assignedUserId(),
                this.name(),
                this.description(),
                this.color(),
                this.calorieAmount(),
                this.startAt(),
                this.dueAt()
        );
    }
}
