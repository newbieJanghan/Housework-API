package com.ssafy.housework.model.workoutCampaign.dto;

import java.time.LocalDateTime;

public record WorkoutCampaignSearch(int familyMemberId, LocalDateTime startAt, LocalDateTime endAt) {
}
