package com.ssafy.housework.controller;

import com.ssafy.housework.model.workoutStat.WorkoutStatService;
import com.ssafy.housework.model.workoutStat.dto.FamilyWorkoutStatsQuery;
import com.ssafy.housework.model.workoutStat.dto.UserWorkoutStatQuery;
import com.ssafy.housework.model.workoutStat.dto.WorkoutStat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/workout-stats")
public class WorkoutStatController {
    private final WorkoutStatService workoutStatService;

    public WorkoutStatController(WorkoutStatService workoutStatService) {
        this.workoutStatService = workoutStatService;
    }

    @GetMapping("/my")
    public WorkoutStat getUserWorkoutStat(UserWorkoutStatQuery query) {
        return workoutStatService.getUserWorkoutStat(query);
    }

    @GetMapping("/my-family")
    public List<WorkoutStat> getFamilyWorkoutStats(FamilyWorkoutStatsQuery query) {
        return workoutStatService.getFamilyWorkoutStats(query);
    }
}
