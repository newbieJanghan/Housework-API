package com.ssafy.housework.core.auth.web.interceptor;

import com.ssafy.housework.core.auth.web.dto.AuthUser;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Admin;
import com.ssafy.housework.core.auth.web.interceptor.annotations.Authenticate;
import jakarta.security.auth.message.AuthException;
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
            Authenticate authenticate = handlerMethod.getMethodAnnotation(Authenticate.class);
            Admin admin = handlerMethod.getMethodAnnotation(Admin.class);

            if (authenticate == null && admin == null) {
                return true;
            }

            AuthUser user = (AuthUser) request.getAttribute(AuthUser.key);

            if (authenticate != null && user == null) {
                throw new AuthException("Need Login");
            }

            if (admin != null && (user == null || !user.isAdmin())) {
                throw new AuthException("Admin Only");
            }
        }
        return true;
    }
}