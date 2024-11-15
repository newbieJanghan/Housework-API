package com.ssafy.housework.model.family.dto;

import com.ssafy.housework.model.user.dto.UserInfo;

import java.util.List;

public record FamilyInfo(
        int id,
        String name,
        String description,
        List<UserInfo> members
) {
}
