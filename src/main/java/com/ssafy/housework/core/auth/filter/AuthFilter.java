package com.ssafy.housework.core.auth.filter;

import com.ssafy.housework.core.auth.token.AuthTokenHandler;
import com.ssafy.housework.core.auth.token.UserTokenInfo;
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
    public static final String USER_TOKEN_INFO_KEY = "userTokenInfo";
    private final AuthTokenHandler authTokenHandler;

    public AuthFilter(@Qualifier("jwt") AuthTokenHandler authTokenHandler) {
        this.authTokenHandler = authTokenHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = AuthHeaderTokenParser.parseBearerToken(request);
        if (token != null) {
            UserTokenInfo userTokenInfo = authTokenHandler.parse(token);
            if (userTokenInfo != null) {
                request.setAttribute(USER_TOKEN_INFO_KEY, userTokenInfo);
            }
        }

        filterChain.doFilter(request, response);
    }
}