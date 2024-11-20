package com.ssafy.housework.core.auth.web.dto;

import jakarta.servlet.http.HttpServletRequest;

public record AuthUser(int id, int familyId, String email, Boolean isAdmin) {
    public static final String key = "auth-user";

    public static AuthUser fromRequest(HttpServletRequest request) {
        return (AuthUser) request.getAttribute(key);
    }
}
