package com.cj.library.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {
    @NotNull(message = "loanDate is required")
    private LocalDate loanDate;

    @NotNull(message = "dueDate is required")
    private LocalDate dueDate;

    @NotNull(message = "returnDate is required")
    private LocalDate returnDate;

    @NotNull(message = "user is required")
    private Long userId;

    @NotNull(message = "book is required")
    private Long bookId;
}
