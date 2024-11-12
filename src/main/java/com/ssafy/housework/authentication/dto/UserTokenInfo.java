package com.ssafy.housework.authentication.dto;

public record UserTokenInfo(int userId, String email, Boolean isAdmin) {
}
