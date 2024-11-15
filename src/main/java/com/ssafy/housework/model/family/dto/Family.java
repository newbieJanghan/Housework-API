package com.ssafy.housework.model.family.dto;

import com.ssafy.housework.model.user.dto.User;
import com.ssafy.housework.model.user.dto.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Family {
    int id;
    String name;
    String description;
    List<User> users;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Family(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public FamilyInfo toFamilyInfo() {
        List<UserInfo> userInfos = users.stream()
                .map(User::toUserInfo)
                .toList();

        return new FamilyInfo(
                id,
                name,
                description,
                userInfos
        );
    }
}