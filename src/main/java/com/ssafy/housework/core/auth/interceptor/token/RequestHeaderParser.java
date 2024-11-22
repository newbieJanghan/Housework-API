package com.ssafy.housework.core.auth.interceptor.token;

import jakarta.servlet.http.HttpServletRequest;

public class RequestHeaderParser {
    public static String parseBearerToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring("Bearer ".length());
        }
        return null;
    }
}
