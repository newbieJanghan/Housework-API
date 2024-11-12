package com.ssafy.housework.model.workoutCampaign.dto;

import java.time.LocalDateTime;

public record WorkoutCampaignCreate(int familyMemberId, int calorieGoal, int calorieBurned, LocalDateTime startAt, LocalDateTime endAt) {
}
