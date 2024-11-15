package com.ssafy.housework.model.workoutStat.dto;

public record WorkoutStat(int userId, int burnedKcal, int goalKcal, int doneHouseworkCnt, int expectedHouseworkCnt) {

}
