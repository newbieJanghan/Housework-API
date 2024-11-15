package com.ssafy.housework.core.auth.token.jwt;

import com.ssafy.housework.core.auth.dto.AuthenticatedUser;
import com.ssafy.housework.core.auth.token.AuthTokenHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("jwt")
public class AuthJwtHandler implements AuthTokenHandler {
    //    private final String SECRET_KEY = "1!8dz@*^!SDd+-df1_2I*827sx";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    @Override
    public String generate(AuthenticatedUser authUser) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");

        Map<String, Object> body = new HashMap<>();
        body.put("userId", authUser.userId());
        body.put("familyId", authUser.familyId());
        body.put("email", authUser.email());
        body.put("isAdmin", authUser.isAdmin());

        return JwtProvider.generateToken(header, body, EXPIRATION_TIME);
    }

    @Override
    public AuthenticatedUser parse(String token) {
        Map<String, Object> body = JwtProvider.parseToken(token);
        if (body != null) {
            return new AuthenticatedUser(
                    (int) body.get("userId"),
                    (int) body.get("familyId"),
                    (String) body.get("email"),
                    (Boolean) body.get("isAdmin")
            );
        }

        return null;
    }
}