package com.cj.library.service.interfaces;

import com.cj.library.dto.request.LoginRequest;
import com.cj.library.dto.request.RegisterRequest;
import com.cj.library.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(String refreshToken);
}
