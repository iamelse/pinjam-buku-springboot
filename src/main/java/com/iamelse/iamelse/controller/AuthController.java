package com.iamelse.iamelse.controller;

import com.iamelse.iamelse.dto.ApiResponse;
import com.iamelse.iamelse.dto.LoginRequest;
import com.iamelse.iamelse.dto.TokenResponse;
import com.iamelse.iamelse.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        if (token != null) {
            return ResponseEntity.ok(new ApiResponse<>("success", "Login berhasil", new TokenResponse(token)));
        } else {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", "Login gagal", null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<TokenResponse>> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", "Authorization header tidak valid", null));
        }

        String token = authHeader.substring(7);

        if (authService.isValidToken(token)) {
            authService.logout(token);
            return ResponseEntity.ok(new ApiResponse<>("success", "Logout berhasil", new TokenResponse(token)));
        } else {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", "Token tidak valid atau tidak ditemukan", null));
        }
    }
}