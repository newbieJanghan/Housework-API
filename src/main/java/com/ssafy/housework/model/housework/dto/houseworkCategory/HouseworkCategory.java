package com.ssafy.housework.model.housework.dto.houseworkCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HouseworkCategory {
    int id;
    int familyId;
    String name;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public HouseworkCategory(int familyId, String name) {
        this.familyId = familyId;
        this.name = name;
    }
}
