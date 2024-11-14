package com.ssafy.housework.model.dailyWorkout;

import com.ssafy.housework.model.dailyWorkout.dto.DailyWorkout;
import com.ssafy.housework.model.dailyWorkout.dto.FamilyWorkoutsQuery;
import com.ssafy.housework.model.dailyWorkout.dto.WorkoutStat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DailyWorkoutDao {
    DailyWorkout selectOne(int id);

    List<DailyWorkout> selectTodayWorkout(int userId);

    List<WorkoutStat> selectFamilyWorkouts(FamilyWorkoutsQuery familyWorkoutsQuery);

    int insert(DailyWorkout dailyWorkout);

    int update(DailyWorkout dailyWorkout);

    int delete(int id);
}