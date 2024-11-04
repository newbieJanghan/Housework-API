package com.ssafy.housework.authentication;

import com.ssafy.housework.authentication.dto.LoginDto;
import com.ssafy.housework.authentication.jwt.JWTUtil;
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
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("invalid token");
            }

            String token = authHeader.substring("Bearer ".length());
            String info = JWTUtil.getInfo(token);
            return ResponseEntity.ok(JWTUtil.generateToken(info));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
