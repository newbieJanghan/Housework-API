package com.ssafy.housework.model.dailyWorkout;

import com.ssafy.housework.model.dailyWorkout.dto.DailyWorkout;
import com.ssafy.housework.model.dailyWorkout.dto.DailyWorkoutSearch;

import java.util.List;

public interface DailyWorkoutDao {
    DailyWorkout selectOne(int id);

    List<DailyWorkout> search(DailyWorkoutSearch search);

    int insert(DailyWorkout dailyWorkout);

    int update(DailyWorkout dailyWorkout);

    int delete(int id);
}