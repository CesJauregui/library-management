package com.cj.library.mapper;

import com.cj.library.dto.request.LoanRequest;
import com.cj.library.dto.response.LoanResponse;
import com.cj.library.entity.Book;
import com.cj.library.entity.Loan;
import com.cj.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "bookId", target = "book")
    Loan toEntity(LoanRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    LoanResponse toDto(Loan loan);

    List<LoanResponse> toDtoList(List<Loan> loans);

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
