package com.ssafy.housework.model.workoutCampaign.dto;

import java.time.LocalDateTime;

public record WorkoutCampaignUpdate(int calorieGoal, int calorieBurned, LocalDateTime startAt, LocalDateTime endAt) {
}