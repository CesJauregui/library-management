package com.cj.library.service.interfaces;

import com.cj.library.dto.request.ReservationRequest;
import com.cj.library.dto.response.ReservationResponse;

import java.util.List;

public interface ReservationService {
    ReservationResponse newReservation(ReservationRequest request);

    List<ReservationResponse> getAllByUser();

    void delete(Long id);
}
