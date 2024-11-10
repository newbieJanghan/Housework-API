package com.ssafy.housework.model.assignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Assignment {
    int id;
    int familyMemberId;
    int taskId;
    int calorieBurned;
    boolean isActive;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Assignment(int familyMemberId, int taskId, int calorieBurned) {
        this.familyMemberId = familyMemberId;
        this.taskId = taskId;
        this.calorieBurned = calorieBurned;
    }
}
