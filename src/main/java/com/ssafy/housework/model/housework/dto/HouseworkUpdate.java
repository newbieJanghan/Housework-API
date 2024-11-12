package com.ssafy.housework.model.housework.dto;

import java.time.LocalDateTime;

public record HouseworkUpdate(
        Integer registerUserId,
        Integer assignedUserId,
        String name,
        String description,
        String color,
        Integer calorieAmount,
        LocalDateTime startAt,
        LocalDateTime dueAt,
        LocalDateTime doneAt
) {

}
