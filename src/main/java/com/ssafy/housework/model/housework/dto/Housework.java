package com.ssafy.housework.model.housework.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Housework {
    int id;
    int familyId;
    int registerUserId;
    Integer assignedUserId; // nullable

    String name;
    String description;
    String color;
    int calorieAmount;

    LocalDateTime startAt;
    LocalDateTime dueAt;
    LocalDateTime doneAt;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Housework(int familyId,
                     int registerUserId,
                     int assignedUserId,
                     String name,
                     String description,
                     String color,
                     int calorieAmount,
                     LocalDateTime startAt,
                     LocalDateTime dueAt
    ) {
        this.familyId = familyId;
        this.registerUserId = registerUserId;
        this.assignedUserId = assignedUserId;
        this.name = name;
        this.description = description;
        this.color = color;
        this.calorieAmount = calorieAmount;
        this.startAt = startAt;
        this.dueAt = dueAt;
    }
}