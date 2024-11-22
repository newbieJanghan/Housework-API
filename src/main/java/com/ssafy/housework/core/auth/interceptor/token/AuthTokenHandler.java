package com.ssafy.housework.core.auth.interceptor.token;

import com.ssafy.housework.core.auth.interceptor.dto.AuthUser;

public interface AuthTokenHandler {
    String generate(AuthUser authUser);

    AuthUser parse(String token) throws InvalidTokenException;
}
