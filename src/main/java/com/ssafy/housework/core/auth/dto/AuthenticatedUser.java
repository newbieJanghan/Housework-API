package com.ssafy.housework.core.auth.dto;

import jakarta.servlet.http.HttpServletRequest;

public record AuthenticatedUser(int userId, int familyId, String email, Boolean isAdmin) {
    public static final String key = "auth-user";

    public static AuthenticatedUser fromRequest(HttpServletRequest request) {
        return (AuthenticatedUser) request.getAttribute(key);
    }
}
