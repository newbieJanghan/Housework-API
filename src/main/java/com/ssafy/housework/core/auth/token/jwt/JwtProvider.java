package com.ssafy.housework.core.auth.token.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtProvider {
    public static String generateToken(
            Map<String, Object> header, Map<String, Object> claims, String secretKey, long expirationTime) {
        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .compact();
    }

    public static Map<String, Object> parseToken(String token, String secretKey) {
        Claims body = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        if (body.getExpiration().before(new Date())) {
            return null;
        }

        return body;
    }
}
