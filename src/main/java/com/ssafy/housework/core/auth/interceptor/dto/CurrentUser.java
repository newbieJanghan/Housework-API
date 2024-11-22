package com.ssafy.housework.core.auth.interceptor.dto;

import com.ssafy.housework.model.user.dto.User;

public record CurrentUser(int id, int familyId, String email, Boolean isAdmin) {
    public static final String key = "auth-user";

    public static CurrentUser of(User user) {
        return new CurrentUser(user.getId(), user.getFamilyId(), user.getEmail(), user.getIsAdmin());
    }
}
