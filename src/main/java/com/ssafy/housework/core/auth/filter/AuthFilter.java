package com.ssafy.housework.core.auth.filter;

import com.ssafy.housework.core.auth.dto.AuthenticatedUser;
import com.ssafy.housework.core.auth.token.AuthTokenHandler;
import com.ssafy.housework.core.auth.util.AuthHeaderTokenParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    private final AuthTokenHandler authTokenHandler;

    public AuthFilter(@Qualifier("jwt") AuthTokenHandler authTokenHandler) {
        this.authTokenHandler = authTokenHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = AuthHeaderTokenParser.parseBearerToken(request);
        if (token != null) {
            AuthenticatedUser authUser = authTokenHandler.parse(token);
            if (authUser != null) {
                request.setAttribute(AuthenticatedUser.key, authUser);
            }
        }

        filterChain.doFilter(request, response);
    }
}