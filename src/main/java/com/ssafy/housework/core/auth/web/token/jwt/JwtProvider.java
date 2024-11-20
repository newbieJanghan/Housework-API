package com.ssafy.housework.core.auth.web.token.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtProvider {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(
            Map<String, Object> header, Map<String, Object> claims, long expirationTime) {

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .compact();
    }

    public static Map<String, Object> parseToken(String token) throws SignatureException {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody();

        if (body.getExpiration().before(new Date())) {
            return null;
        }

        return body;
    }
}
