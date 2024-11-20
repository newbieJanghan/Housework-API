package com.ssafy.housework.model.housework.dto;

import java.time.LocalDateTime;

public record HouseworkQuery(int familyId, Integer assignedUserId, LocalDateTime from, LocalDateTime to) {
    public HouseworkQuery {
        if (familyId <= 0) {
            throw new IllegalArgumentException("Family id must be positive");
        }

        if (from == null || to == null) {
            throw new IllegalArgumentException("From and to must not be null");
        }

        if (from.isAfter(to)) {
            throw new IllegalArgumentException("From must be before to");
        }
    }
}
