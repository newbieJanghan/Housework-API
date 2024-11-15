package com.ssafy.housework.core.auth.token;

public record UserTokenInfo(int userId, int familyId, String email, Boolean isAdmin) {
}
