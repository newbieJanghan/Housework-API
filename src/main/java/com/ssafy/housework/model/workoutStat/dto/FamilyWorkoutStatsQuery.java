package com.ssafy.housework.model.workoutStat.dto;

import java.time.LocalDateTime;

public record FamilyWorkoutStatsQuery(int familyId, LocalDateTime from, LocalDateTime to) {

}
