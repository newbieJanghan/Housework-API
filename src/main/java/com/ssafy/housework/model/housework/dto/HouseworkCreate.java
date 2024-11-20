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
