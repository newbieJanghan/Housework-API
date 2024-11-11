package com.ssafy.housework.model.task.dto;

import java.time.LocalDateTime;

public record TaskCreate(int familyId, int houseworkId, String name, String description, LocalDateTime dueAt) {
}