package com.ssafy.housework.core.auth.token;

public interface AuthTokenHandler {
    String generate(UserTokenInfo userTokenInfo);

    UserTokenInfo parse(String token);
}
