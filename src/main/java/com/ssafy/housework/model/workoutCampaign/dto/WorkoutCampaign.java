package com.ssafy.housework.model.workoutCampaign.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class WorkoutCampaign {
    private int id;
    private int familyMemberId;
    private int calorieGoal;
    private int calorieBurned;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public WorkoutCampaign(int familyMemberId, int calorieGoal, int calorieBurned, LocalDateTime startAt, LocalDateTime endAt) {
        this.familyMemberId = familyMemberId;
        this.calorieGoal = calorieGoal;
        this.calorieBurned = calorieBurned;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
