package com.ssafy.housework.controller.utils;

import java.time.LocalDateTime;

public record DateQueryParams(LocalDateTime from, LocalDateTime to) {
}
