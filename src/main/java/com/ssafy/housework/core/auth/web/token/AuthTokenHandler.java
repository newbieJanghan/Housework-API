package com.ssafy.housework.core.auth.web.token;

import com.ssafy.housework.core.auth.web.dto.AuthUser;

public interface AuthTokenHandler {
    String generate(AuthUser authUser);

    AuthUser parse(String token);
}
