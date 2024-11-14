package com.ssafy.housework.model.dailyWorkout.dto;

import java.time.LocalDateTime;

public record WorkoutStat(int userId, int burnedKcal, int goalKcal, int doneHouseworkCnt, int expectedHouseworkCnt,
                          LocalDateTime from, LocalDateTime to) {

}
