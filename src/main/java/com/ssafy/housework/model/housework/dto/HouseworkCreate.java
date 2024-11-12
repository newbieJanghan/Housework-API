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

}
