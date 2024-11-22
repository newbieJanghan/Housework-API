package com.ssafy.housework.core.auth.interceptor.token.jwt;

import com.ssafy.housework.core.auth.interceptor.dto.CurrentUser;
import com.ssafy.housework.core.auth.interceptor.token.AuthTokenHandler;
import com.ssafy.housework.core.auth.interceptor.token.InvalidTokenException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("jwt")
public class AuthJwtHandler implements AuthTokenHandler {
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    private final String KEY_ID = "id";
    private final String KEY_FAMILY_ID = "familyId";
    private final String KEY_EMAIL = "email";
    private final String KEY_IS_ADMIN = "isAdmin";

    @Override
    public String generate(CurrentUser currentUser) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");

        Map<String, Object> body = new HashMap<>();
        body.put(KEY_ID, currentUser.id());
        body.put(KEY_FAMILY_ID, currentUser.familyId());
        body.put(KEY_EMAIL, currentUser.email());
        body.put(KEY_IS_ADMIN, currentUser.isAdmin());

        return JwtProvider.generateToken(header, body, EXPIRATION_TIME);
    }

    @Override
    public CurrentUser parse(String token) {
        try {
            Map<String, Object> body = JwtProvider.parseToken(token);

            if (body == null) {
                throw new InvalidTokenException("Token is expired");
            }

            return new CurrentUser(
                    (int) body.get(KEY_ID),
                    (int) body.get(KEY_FAMILY_ID),
                    (String) body.get(KEY_EMAIL),
                    (Boolean) body.get(KEY_IS_ADMIN)
            );

        } catch (SignatureException ex) {
            throw new InvalidTokenException(ex);
        }
    }
}