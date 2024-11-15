package com.ssafy.housework.model.workoutStat;

import com.ssafy.housework.model.workoutStat.dto.FamilyWorkoutStatsQuery;
import com.ssafy.housework.model.workoutStat.dto.UserWorkoutStatQuery;
import com.ssafy.housework.model.workoutStat.dto.WorkoutStat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkoutStatDao {
    WorkoutStat getUserWorkoutStat(UserWorkoutStatQuery query);

    List<WorkoutStat> getFamilyWorkoutStats(FamilyWorkoutStatsQuery query);
}
