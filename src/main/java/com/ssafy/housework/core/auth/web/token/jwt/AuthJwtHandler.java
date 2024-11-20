package com.ssafy.housework.core.auth.web.token.jwt;

import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.token.AuthTokenHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("jwt")
public class AuthJwtHandler implements AuthTokenHandler {
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    private final String KEY_ID = "id";
    private final String KEY_FAMILY_ID = "familyId";
    private final String KEY_EMAIL = "email";
    private final String KEY_IS_ADMIN = "isAdmin";

    @Override
    public String generate(AuthUser authUser) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");

        Map<String, Object> body = new HashMap<>();
        body.put(KEY_ID, authUser.id());
        body.put(KEY_FAMILY_ID, authUser.familyId());
        body.put(KEY_EMAIL, authUser.email());
        body.put(KEY_IS_ADMIN, authUser.isAdmin());

        return JwtProvider.generateToken(header, body, EXPIRATION_TIME);
    }

    @Override
    public AuthUser parse(String token) {
        Map<String, Object> body = JwtProvider.parseToken(token);
        if (body != null) {
            return new AuthUser(
                    (int) body.get(KEY_ID),
                    (int) body.get(KEY_FAMILY_ID),
                    (String) body.get(KEY_EMAIL),
                    (Boolean) body.get(KEY_IS_ADMIN)
            );
        }

        return null;
    }
}