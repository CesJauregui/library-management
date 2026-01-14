package com.cj.library.controller;

import com.cj.library.dto.request.LoanRequest;
import com.cj.library.dto.response.LoanResponse;
import com.cj.library.dto.response.MessageResponse;
import com.cj.library.service.interfaces.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Slf4j
public class LoanController {
    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        log.info("GET /api/loans - Getting all loans by user");
        return ResponseEntity.status(HttpStatus.OK).body(loanService.getAllByUser());
    }

    @PostMapping
    public ResponseEntity<LoanResponse> postNewLoan(@Valid @RequestBody LoanRequest request) {
        log.info("POST /api/loans - Creating new loan");
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.newLoan(request));
    }

    @PutMapping("/{id}/renew")
    public ResponseEntity<LoanResponse> putRenewLoan(@PathVariable Long id, @Valid @RequestBody LoanRequest request) {
        log.info("PUT /api/loans/{id}/renew - Renewing loan: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(loanService.renewLoan(id, request));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<MessageResponse> putReturnBook(@PathVariable Long id) {
        log.info("POST /api/loans/{id}/return - Returning book - Loan status update");
        return ResponseEntity.status(HttpStatus.OK).body(loanService.returnBook(id));
    }

}
