package com.ssafy.housework.authentication.jwt;

import com.ssafy.housework.authentication.dto.UserInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length());
            if (JWTUtil.verifyToken(token)) {
                UserInfo info = UserInfoHandler.extractUserInfo(JWTUtil.getInfo(token));
                request.setAttribute("user", info);
            }
        }

        filterChain.doFilter(request, response);
    }
}