package com.ssafy.housework.model.dailyWorkout;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DailyWorkout {
    int id;
    int userId;
    int calorieBurned;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public DailyWorkout(int userId, int calorieBurned) {
        this.userId = userId;
        this.calorieBurned = calorieBurned;
    }
}
