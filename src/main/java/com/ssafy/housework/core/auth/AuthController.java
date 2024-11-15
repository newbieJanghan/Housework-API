package com.ssafy.housework.core.auth;

import com.ssafy.housework.core.auth.dto.LoginDto;
import com.ssafy.housework.core.auth.util.AuthHeaderTokenParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        try {
            return ResponseEntity.ok(authService.login(loginDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/extend")
    public ResponseEntity<String> extend(HttpServletRequest request) {
        try {
            String token = AuthHeaderTokenParser.parseBearerToken(request);
            return ResponseEntity.ok(authService.extend(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
