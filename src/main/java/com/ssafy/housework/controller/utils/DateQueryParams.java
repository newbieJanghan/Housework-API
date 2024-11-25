package com.ssafy.housework.controller.utils;

import com.ssafy.housework.core.utils.TimeUtil;

import java.time.LocalDateTime;

public record DateQueryParams(LocalDateTime from, LocalDateTime to) {
    public DateQueryParams {
        from = TimeUtil.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        to = TimeUtil.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
    }
}