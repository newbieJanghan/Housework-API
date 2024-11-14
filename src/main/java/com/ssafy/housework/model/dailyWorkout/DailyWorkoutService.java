package com.ssafy.housework.model.dailyWorkout;

import com.ssafy.housework.model.dailyWorkout.dto.DailyWorkout;
import com.ssafy.housework.model.dailyWorkout.dto.FamilyWorkoutsQuery;
import com.ssafy.housework.model.dailyWorkout.dto.WorkoutStat;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyWorkoutService {
    private final DailyWorkoutDao dailyWorkoutDao;

    public DailyWorkoutService(DailyWorkoutDao dailyWorkoutDao) {
        this.dailyWorkoutDao = dailyWorkoutDao;
    }

    public DailyWorkout getOne(int id) {
        return dailyWorkoutDao.selectOne(id);
    }

    public List<WorkoutStat> getFamilyWorkoutStats(FamilyWorkoutsQuery query) {
        List<DailyWorkout> dailyWorkouts = dailyWorkoutDao.selectFamilyWorkouts(query);

    }

    public DailyWorkout create(DailyWorkout dailyWorkout) {
        int result = dailyWorkoutDao.insert(dailyWorkout);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to create daily workout");
        }

        return dailyWorkout;
    }

    public DailyWorkout update(DailyWorkout dailyWorkout) {
        int result = dailyWorkoutDao.update(dailyWorkout);
        if (result == 0) {
            throw new DataAccessResourceFailureException("Failed to update daily workout");
        }

        return dailyWorkout;
    }

    public void delete(int id) {
        dailyWorkoutDao.delete(id);
    }
}
