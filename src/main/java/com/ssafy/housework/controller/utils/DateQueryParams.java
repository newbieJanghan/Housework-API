package com.ssafy.housework.controller.utils;

import com.ssafy.housework.controller.exceptions.BadRequestException;
import com.ssafy.housework.core.utils.TimeUtil;

import java.time.LocalDateTime;

public record DateQueryParams(LocalDateTime from, LocalDateTime to) {
    public DateQueryParams {
        if (from == null) {
            from = TimeUtil.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        }
        if (to == null) {
            to = TimeUtil.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        }

        if (from.isAfter(to)) {
            throw new BadRequestException("from must be before to");
        }
    }
}