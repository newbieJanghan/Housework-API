package com.ssafy.housework.model.dailyWorkout.dto;

import java.time.LocalDateTime;

public record FamilyWorkoutsQuery(int familyId, LocalDateTime from, LocalDateTime to) {

}
