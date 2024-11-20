package com.ssafy.housework.core.auth.web.token;

import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.filter.InvalidTokenException;

public interface AuthTokenHandler {
    String generate(AuthUser authUser);

    AuthUser parse(String token) throws InvalidTokenException;
}
