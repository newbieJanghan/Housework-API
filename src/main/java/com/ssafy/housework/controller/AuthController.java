package com.ssafy.housework.controller;

import com.ssafy.housework.core.auth.interceptor.token.RequestHeaderParser;
import com.ssafy.housework.core.auth.service.AuthService;
import com.ssafy.housework.core.auth.service.dto.LoginRequest;
import com.ssafy.housework.core.auth.service.dto.SignupRequest;
import com.ssafy.housework.core.auth.service.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "회원가입")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "회원가입 성공", content = {@Content(schema = @Schema(implementation = TokenResponse.class))}),
//            @ApiResponse(responseCode = "400", description = "중복 이메일", content = {@Content(schema = @Schema(implementation = ProblemDetail.class))})
//    })
    @PostMapping("/signup")
    public TokenResponse signup(@RequestBody SignupRequest signupRequest) {
        return authService.signup(signupRequest);
    }

    @Operation(summary = "로그인")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "로그인 성공", content = {@Content(schema = @Schema(implementation = TokenResponse.class))}),
//            @ApiResponse(responseCode = "400", description = "비밀번호 불일치", content = {@Content(schema = @Schema(implementation = ProblemDetail.class))}),
//            @ApiResponse(responseCode = "404", description = "이메일 없음", content = {@Content(schema = @Schema(implementation = ProblemDetail.class))})
//    })
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
