package com.ssafy.housework.model.family.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Family {
    int id;
    String name;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Family(String name, String description) {
        this.name = name;
        this.description = description;
    }
}