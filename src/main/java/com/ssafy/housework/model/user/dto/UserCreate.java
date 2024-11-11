package com.ssafy.housework.model.user.dto;

public record UserCreate(int familyId, String name, String email, String password, String profileImageUrl) {

}
