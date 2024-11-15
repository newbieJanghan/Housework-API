package com.ssafy.housework.model.dailyWorkout;

import com.ssafy.housework.model.workoutStat.dto.FamilyWorkoutStatsQuery;
import com.ssafy.housework.model.workoutStat.dto.WorkoutStat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DailyWorkoutDao {
    DailyWorkout selectOne(int id);

    List<DailyWorkout> selectTodayWorkout(int userId);

    List<WorkoutStat> selectFamilyWorkouts(FamilyWorkoutStatsQuery familyWorkoutsQuery);

    int insert(DailyWorkout dailyWorkout);

    int update(DailyWorkout dailyWorkout);

    int delete(int id);
}