package com.ssafy.housework.core.auth.service.dto;

import com.ssafy.housework.model.user.dto.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SignupRequest {
    @Setter
    Integer familyId;
    String name;
    String email;
    String password;
    String familyName;

    public String getFamilyName() {
        return familyName == null ? name + "'s Family" : familyName;
    }

    public User toUser() {
        return new User(familyId, name, email, password, null, 0);
    }

}
