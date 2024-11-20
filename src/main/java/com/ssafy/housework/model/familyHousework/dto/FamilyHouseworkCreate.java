package com.ssafy.housework.model.familyHousework.dto;

import com.ssafy.housework.model.housework.dto.Housework;

import java.time.LocalDateTime;

public record FamilyHouseworkCreate(
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
