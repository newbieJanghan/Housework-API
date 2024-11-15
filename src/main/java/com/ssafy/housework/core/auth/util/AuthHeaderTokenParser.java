package com.ssafy.housework.core.auth.util;

import jakarta.servlet.http.HttpServletRequest;

public class AuthHeaderTokenParser {
    public static String parseBearerToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring("Bearer ".length());
        }
        return null;
    }
}
