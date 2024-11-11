package com.ssafy.housework.model.task.dto;

import java.time.LocalDateTime;

public record TaskUpdate(String name, String description, LocalDateTime dueAt) {
    
}
