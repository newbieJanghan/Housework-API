package com.ssafy.housework.core.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeUtil {
    private static final ZoneId KST = ZoneId.of("Asia/Seoul");

    public static LocalDateTime now() {
        return LocalDateTime.now(KST);
    }
}
