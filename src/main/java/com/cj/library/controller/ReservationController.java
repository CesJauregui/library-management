package com.cj.library.controller;

import com.cj.library.dto.request.ReservationRequest;
import com.cj.library.dto.response.ReservationResponse;
import com.cj.library.service.interfaces.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reservations")
@Slf4j
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        log.info("GET /api/reservations - Getting all reservations");
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllByUser());
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> postReservation(@Valid @RequestBody ReservationRequest request) {
        log.info("POST /api/reservations - Creating new reservation");
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.newReservation(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        log.info("DELETE /api/reservations/{id} - Deleting reservation: {}", id);
        reservationService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
