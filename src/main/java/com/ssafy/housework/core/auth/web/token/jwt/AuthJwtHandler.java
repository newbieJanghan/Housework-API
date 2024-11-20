package com.ssafy.housework.core.auth.web.token.jwt;

import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.token.AuthTokenHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("jwt")
public class AuthJwtHandler implements AuthTokenHandler {
    //    private final String SECRET_KEY = "1!8dz@*^!SDd+-df1_2I*827sx";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    @Override
    public String generate(AuthUser authUser) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");

        Map<String, Object> body = new HashMap<>();
        body.put("userId", authUser.id());
        body.put("familyId", authUser.familyId());
        body.put("email", authUser.email());
        body.put("isAdmin", authUser.isAdmin());

        return JwtProvider.generateToken(header, body, EXPIRATION_TIME);
    }

    @Override
    public AuthUser parse(String token) {
        Map<String, Object> body = JwtProvider.parseToken(token);
        if (body != null) {
            return new AuthUser(
                    (int) body.get("id"),
                    (int) body.get("familyId"),
                    (String) body.get("email"),
                    (Boolean) body.get("isAdmin")
            );
        }

        return null;
    }
}