package com.ssafy.housework.core.auth.interceptor.token;

import com.ssafy.housework.core.auth.interceptor.dto.CurrentUser;

public interface AuthTokenHandler {
    String generate(CurrentUser currentUser);

    CurrentUser parse(String token) throws InvalidTokenException;
}
