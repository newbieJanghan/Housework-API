package com.ssafy.housework.model.dailyWorkout;

import com.ssafy.housework.model.dailyWorkout.dto.DailyWorkout;
import com.ssafy.housework.model.dailyWorkout.dto.FamilyWorkoutsQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DailyWorkoutDao {
    DailyWorkout selectOne(int id);

    List<DailyWorkout> selectTodayWorkout(int userId);

    List<DailyWorkout> selectFamilyWorkouts(FamilyWorkoutsQuery familyWorkoutsQuery);

    int insert(DailyWorkout dailyWorkout);

    int update(DailyWorkout dailyWorkout);

    int delete(int id);
}