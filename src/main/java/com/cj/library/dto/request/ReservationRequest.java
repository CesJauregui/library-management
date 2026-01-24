package com.cj.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    @NotNull(message = "reservationDate is required")
    private LocalDate reservationDate;

    @NotNull(message = "expirationDate is required")
    private LocalDate expirationDate;

    @NotNull(message = "user is required")
    private Long userId;

    @NotNull(message = "book is required")
    private Long bookId;
}
