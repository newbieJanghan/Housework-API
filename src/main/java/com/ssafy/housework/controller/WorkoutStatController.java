package com.ssafy.housework.controller;

import com.ssafy.housework.controller.exceptions.BadRequestException;
import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.web.resolvers.CurrentUser;
import com.ssafy.housework.model.workoutStat.WorkoutStatService;
import com.ssafy.housework.model.workoutStat.dto.FamilyWorkoutStatsQuery;
import com.ssafy.housework.model.workoutStat.dto.UserWorkoutStatQuery;
import com.ssafy.housework.model.workoutStat.dto.WorkoutStat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workout-stats")
public class WorkoutStatController {
    private final WorkoutStatService workoutStatService;

    public WorkoutStatController(WorkoutStatService workoutStatService) {
        this.workoutStatService = workoutStatService;
    }

    @Authenticate
    @GetMapping("/my")
    public WorkoutStat getUserWorkoutStat(@CurrentUser AuthUser user, UserWorkoutStatQuery query) throws BadRequestException {
        if (user.id() != query.userId()) {
            throw new BadRequestException("Invalid User Id");
        }

        return workoutStatService.getUserWorkoutStat(query);
    }

    @Authenticate
    @GetMapping("/my-family")
    public List<WorkoutStat> getFamilyWorkoutStats(@CurrentUser AuthUser user, FamilyWorkoutStatsQuery query) throws BadRequestException {
        if (user.familyId() != query.familyId()) {
            throw new BadRequestException("Invalid Family Id");
        }

        return workoutStatService.getFamilyWorkoutStats(query);
    }
}
