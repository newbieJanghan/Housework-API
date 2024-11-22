package com.ssafy.housework.controller;

import com.ssafy.housework.controller.exceptions.BadRequestException;
import com.ssafy.housework.controller.utils.DateQueryParams;
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
    public WorkoutStat getUserWorkoutStat(@CurrentUser AuthUser user, DateQueryParams dateQuery) throws BadRequestException {
        return workoutStatService.getUserWorkoutStat(new UserWorkoutStatQuery(user.id(), dateQuery.from(), dateQuery.to()));
    }

    @Authenticate
    @GetMapping("/my-family")
    public List<WorkoutStat> getFamilyWorkoutStats(@CurrentUser AuthUser user, DateQueryParams dateQuery) throws BadRequestException {
        return workoutStatService.getFamilyWorkoutStats(new FamilyWorkoutStatsQuery(user.familyId(), dateQuery.from(), dateQuery.to()));
    }
}
