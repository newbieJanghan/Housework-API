package com.ssafy.housework.controller;

import com.ssafy.housework.controller.exceptions.BadRequestException;
import com.ssafy.housework.core.auth.annotations.Authentication;
import com.ssafy.housework.core.auth.dto.AuthenticatedUser;
import com.ssafy.housework.model.workoutStat.WorkoutStatService;
import com.ssafy.housework.model.workoutStat.dto.FamilyWorkoutStatsQuery;
import com.ssafy.housework.model.workoutStat.dto.UserWorkoutStatQuery;
import com.ssafy.housework.model.workoutStat.dto.WorkoutStat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
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

    @Authentication
    @GetMapping("/my")
    public WorkoutStat getUserWorkoutStat(@RequestAttribute(AuthenticatedUser.key) AuthenticatedUser user, UserWorkoutStatQuery query) throws BadRequestException {
        if (user.userId() != query.userId()) {
            throw new BadRequestException("Invalid User Id");
        }

        return workoutStatService.getUserWorkoutStat(query);
    }

    @Authentication
    @GetMapping("/my-family")
    public List<WorkoutStat> getFamilyWorkoutStats(@RequestAttribute(AuthenticatedUser.key) AuthenticatedUser user, FamilyWorkoutStatsQuery query) throws BadRequestException {
        if (user.familyId() != query.familyId()) {
            throw new BadRequestException("Invalid Family Id");
        }

        return workoutStatService.getFamilyWorkoutStats(query);
    }
}
