package com.cj.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate reservationDate;
    private LocalDate expirationDate;
    private String status;
}
