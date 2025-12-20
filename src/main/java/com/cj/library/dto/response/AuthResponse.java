package com.cj.library.dto.response;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Baerer";
    private Long expiresIn;
    private UserResponse user;
}
