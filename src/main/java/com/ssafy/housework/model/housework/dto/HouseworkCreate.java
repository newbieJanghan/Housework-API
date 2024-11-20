package com.ssafy.housework.model.housework.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record HouseworkCreate(
        @NotNull int assignedUserId,
        @NotNull String name,
        String description,
        String color,
        int calorieAmount,
        @NotNull LocalDateTime startAt,
        @NotNull LocalDateTime dueAt
) {
    public HouseworkCreate {
        if (assignedUserId <= 0) {
            throw new IllegalArgumentException("Assigned user id must be positive");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
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
