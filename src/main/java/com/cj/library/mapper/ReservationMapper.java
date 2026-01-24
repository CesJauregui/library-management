package com.cj.library.mapper;

import com.cj.library.dto.request.ReservationRequest;
import com.cj.library.dto.response.ReservationResponse;
import com.cj.library.entity.Book;
import com.cj.library.entity.Reservation;
import com.cj.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "bookId", target = "book")
    Reservation toEntity(ReservationRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    ReservationResponse toDto(Reservation reservation);

    List<ReservationResponse> toDtoList(List<Reservation> reservations);

    default User mapUser(Long userId) {
        if (userId == null) return null;
        User user = new User();
        user.setId(userId);
        return user;
    }

    default Book mapBook(Long bookId) {
        if (bookId == null) return null;
        Book book = new Book();
        book.setId(bookId);
        return book;
    }
}
