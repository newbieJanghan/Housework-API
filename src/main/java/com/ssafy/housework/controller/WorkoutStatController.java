package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.dto.AuthenticatedUser;
import com.ssafy.housework.core.auth.interceptor.annotations.Authentication;
import com.ssafy.housework.model.workoutStat.WorkoutStatService;
import com.ssafy.housework.model.workoutStat.dto.FamilyWorkoutStatsQuery;
import com.ssafy.housework.model.workoutStat.dto.UserWorkoutStatQuery;
import com.ssafy.housework.model.workoutStat.dto.WorkoutStat;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getUserWorkoutStat(@RequestAttribute(AuthenticatedUser.key) AuthenticatedUser user, UserWorkoutStatQuery query) {
        if (user.userId() != query.userId()) {
            return ResponseEntity.badRequest().body("Invalid User Id");
        }

        WorkoutStat workoutStat = workoutStatService.getUserWorkoutStat(query);
        if (workoutStat == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(workoutStat);
    }

    @Authentication
    @GetMapping("/my-family")
    public ResponseEntity<?> getFamilyWorkoutStats(@RequestAttribute(AuthenticatedUser.key) AuthenticatedUser user, FamilyWorkoutStatsQuery query) {
        if (user.familyId() != query.familyId()) {
            return ResponseEntity.badRequest().body("Invalid Family Id");
        }

        List<WorkoutStat> result = workoutStatService.getFamilyWorkoutStats(query);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
}
