package com.ssafy.housework.core.auth.token;

import com.ssafy.housework.core.auth.dto.AuthenticatedUser;

public interface AuthTokenHandler {
    String generate(AuthenticatedUser authUser);

    AuthenticatedUser parse(String token);
}
