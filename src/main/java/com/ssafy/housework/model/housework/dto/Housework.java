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
    Integer houseworkCategoryId; // nullable
    String name;
    int calorieAmount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Housework(int familyId, String name, int calorieAmount) {
        this.familyId = familyId;
        this.name = name;
        this.calorieAmount = calorieAmount;
    }
}