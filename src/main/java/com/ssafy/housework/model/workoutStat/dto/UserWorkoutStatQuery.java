package com.ssafy.housework.model.workoutStat.dto;

import java.time.LocalDateTime;

public record UserWorkoutStatQuery(int userId, LocalDateTime from, LocalDateTime to) {

}
