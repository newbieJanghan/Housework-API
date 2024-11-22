package com.ssafy.housework.core.auth.interceptor;

import com.ssafy.housework.core.auth.exceptions.AuthException;
import com.ssafy.housework.core.auth.exceptions.ForbiddenException;
import com.ssafy.housework.core.auth.interceptor.annotations.Admin;
import com.ssafy.housework.core.auth.interceptor.annotations.Authenticate;
import com.ssafy.housework.core.auth.interceptor.dto.AuthUser;
import com.ssafy.housework.core.auth.interceptor.token.AuthTokenHandler;
import com.ssafy.housework.core.auth.interceptor.token.RequestHeaderParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final AuthTokenHandler authTokenHandler;

    public AuthInterceptor(@Qualifier("jwt") AuthTokenHandler authTokenHandler) {
        this.authTokenHandler = authTokenHandler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Authenticate authenticate = handlerMethod.getMethodAnnotation(Authenticate.class);
            Admin admin = handlerMethod.getMethodAnnotation(Admin.class);

            if (authenticate == null && admin == null) {
                return true;
            }

            AuthUser user = parseToken(request);
            if (user == null) {
                throw new AuthException("Need Login");
            }

            if (admin != null && !user.isAdmin()) {
                throw new ForbiddenException("Admin Only");
            }
        }

        return true;
    }

    @Nullable
    private AuthUser parseToken(HttpServletRequest request) {
        String bearerToken = RequestHeaderParser.parseBearerToken(request);
        if (bearerToken == null) {
            return null;
        }

        return authTokenHandler.parse(bearerToken);
    }
}