package com.cj.library.dto.response;

import com.cj.library.enums.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private Boolean isActive;
}
