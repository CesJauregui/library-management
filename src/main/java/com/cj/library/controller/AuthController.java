package com.cj.library.controller;

import com.cj.library.dto.request.LoginRequest;
import com.cj.library.dto.request.RefreshTokenRequest;
import com.cj.library.dto.request.RegisterRequest;
import com.cj.library.dto.response.AuthResponse;
import com.cj.library.service.interfaces.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Registrar un nuevo usuario
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        log.info("POST /api/auth/register - Registering user: {}", request.getUsername());
        AuthResponse response = authService.register(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Iniciar Sesión
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        log.info("POST /api/auth/login - Login attempt for: {}", request.getUsername());
        AuthResponse response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Renovar access token con refresh token
     */
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request){
        log.info("POST /api/auth/refresh - Refreshing token");
        AuthResponse response = authService.refreshToken(request.getToken());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint de prueba para verificar autenticación
     */
    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser() {
        return ResponseEntity.ok("User is authenticated!");
    }
}
