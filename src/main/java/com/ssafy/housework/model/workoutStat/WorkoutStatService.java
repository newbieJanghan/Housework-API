package com.ssafy.housework.model.workoutStat;

import com.ssafy.housework.model.workoutStat.dto.FamilyWorkoutStatsQuery;
import com.ssafy.housework.model.workoutStat.dto.UserWorkoutStatQuery;
import com.ssafy.housework.model.workoutStat.dto.WorkoutStat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutStatService {
    private final WorkoutStatDao workoutStatDao;

    public WorkoutStatService(WorkoutStatDao workoutStatDao) {
        this.workoutStatDao = workoutStatDao;
    }

    public WorkoutStat getUserWorkoutStat(UserWorkoutStatQuery query) {
        return workoutStatDao.getUserWorkoutStat(query);
    }

    public List<WorkoutStat> getFamilyWorkoutStats(FamilyWorkoutStatsQuery query) {
        return workoutStatDao.getFamilyWorkoutStats(query);
    }

}
