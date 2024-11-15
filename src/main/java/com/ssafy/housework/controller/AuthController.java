package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.AuthService;
import com.ssafy.housework.core.auth.dto.LoginRequest;
import com.ssafy.housework.core.auth.dto.TokenResponse;
import com.ssafy.housework.core.auth.util.AuthHeaderTokenParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/extend")
    public TokenResponse extend(HttpServletRequest request) {
        String token = AuthHeaderTokenParser.parseBearerToken(request);
        return authService.extend(token);
    }

}
