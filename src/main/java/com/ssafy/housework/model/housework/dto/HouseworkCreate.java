package com.ssafy.housework.model.housework.dto;

import java.time.LocalDateTime;

public record HouseworkCreate(
        int familyId,
        int registerUserId,
        int assignedUserId,
        String name,
        String description,
        String color,
        int calorieAmount,
        LocalDateTime startAt,
        LocalDateTime dueAt
) {
    public Housework toHousework() {
        return new Housework(
                this.familyId(),
                this.registerUserId(),
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
