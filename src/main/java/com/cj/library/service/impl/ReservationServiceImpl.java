package com.cj.library.service.impl;

import com.cj.library.dto.request.ReservationRequest;
import com.cj.library.dto.response.ReservationResponse;
import com.cj.library.entity.User;
import com.cj.library.exception.ResourceNotFoundException;
import com.cj.library.mapper.ReservationMapper;
import com.cj.library.repository.BookRepository;
import com.cj.library.repository.ReservationRepository;
import com.cj.library.repository.UserRepository;
import com.cj.library.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponse> getAllByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return reservationMapper.toDtoList(reservationRepository.findAllByUserId(user.getId()));
    }

    @Override
    @Transactional
    public ReservationResponse newReservation(ReservationRequest request) {
        userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return bookRepository.findById(request.getBookId())
                .map(book -> reservationMapper.toDto(
                        reservationRepository.save(reservationMapper.toEntity(request)
                        )
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        reservationRepository.findById(id)
                .ifPresentOrElse(reservationRepository::delete,
                        () -> new ResourceNotFoundException("Reservation not found"));
    }
}
