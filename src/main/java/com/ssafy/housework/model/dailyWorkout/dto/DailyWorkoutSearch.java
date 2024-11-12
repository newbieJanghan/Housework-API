package com.ssafy.housework.model.dailyWorkout.dto;

import java.time.LocalDateTime;

public record DailyWorkoutSearch(int userId, LocalDateTime date) {
}
