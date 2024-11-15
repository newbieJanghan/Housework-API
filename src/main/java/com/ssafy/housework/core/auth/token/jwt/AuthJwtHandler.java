package com.ssafy.housework.core.auth.token.jwt;

import com.ssafy.housework.core.auth.token.AuthTokenHandler;
import com.ssafy.housework.core.auth.token.UserTokenInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("jwt")
public class AuthJwtHandler implements AuthTokenHandler {
    private final String SECRET_KEY = "baejangpan";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;


    @Override
    public String generate(UserTokenInfo userTokenInfo) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");

        Map<String, Object> body = new HashMap<>();
        body.put("userId", userTokenInfo.userId());
        body.put("familyId", userTokenInfo.familyId());
        body.put("email", userTokenInfo.email());
        body.put("isAdmin", userTokenInfo.isAdmin());

        return JwtProvider.generateToken(header, body, SECRET_KEY, EXPIRATION_TIME);
    }

    @Override
    public UserTokenInfo parse(String token) {
        Map<String, Object> body = JwtProvider.parseToken(token, SECRET_KEY);
        if (body != null) {
            return new UserTokenInfo(
                    (int) body.get("userId"),
                    (int) body.get("familyId"),
                    (String) body.get("email"),
                    (Boolean) body.get("isAdmin")
            );
        }

        return null;
    }
}