package com.ssafy.housework.controller.utils;

import java.time.LocalDateTime;

public record DateQueryParams(LocalDateTime from, LocalDateTime to) {
    public DateQueryParams {
        from = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        to = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
    }
}