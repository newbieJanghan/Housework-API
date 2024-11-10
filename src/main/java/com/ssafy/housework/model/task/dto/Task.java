package com.ssafy.housework.model.task.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Task {
    int id;
    int familyId;
    int houseworkId;
    String name;
    String description;
    LocalDateTime dueAt;
    LocalDateTime doneAt;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Task(int familyId, int houseworkId, String name, String description, LocalDateTime dueAt) {
        this.familyId = familyId;
        this.houseworkId = houseworkId;
        this.name = name;
        this.description = description;
        this.dueAt = dueAt;
    }
}