package com.ssafy.housework.model.familyHousework.dto;

import java.time.LocalDateTime;

public record FamilyHouseworkQuery(FamilyHouseworkDateQuery date, int assignedUserId) {
    public record FamilyHouseworkDateQuery(LocalDateTime from, LocalDateTime to) {
    }
}
