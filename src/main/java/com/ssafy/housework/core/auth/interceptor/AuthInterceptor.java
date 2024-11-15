package com.ssafy.housework.core.auth.interceptor;

import com.ssafy.housework.core.auth.dto.AuthenticatedUser;
import com.ssafy.housework.core.auth.interceptor.annotations.Admin;
import com.ssafy.housework.core.auth.interceptor.annotations.Authentication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Authentication authentication = handlerMethod.getMethodAnnotation(Authentication.class);
            Admin admin = handlerMethod.getMethodAnnotation(Admin.class);

            if (authentication == null && admin == null) {
                return true;
            }

            AuthenticatedUser user = AuthenticatedUser.fromRequest(request);

            if (authentication != null && user == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            if (admin != null && (user == null || !user.isAdmin())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }
        return true;
    }
}