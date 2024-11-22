package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.interceptor.token.RequestHeaderParser;
import com.ssafy.housework.core.auth.service.AuthService;
import com.ssafy.housework.core.auth.service.dto.LoginRequest;
import com.ssafy.housework.core.auth.service.dto.SignupRequest;
import com.ssafy.housework.core.auth.service.dto.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public TokenResponse signup(@RequestBody SignupRequest signupRequest) {
        return authService.signup(signupRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/extend")
    public TokenResponse extend(HttpServletRequest request) {
        String token = RequestHeaderParser.parseBearerToken(request);
        return authService.extend(token);
    }

}
