package com.cj.library.mapper;

import com.cj.library.dto.request.BookRequest;
import com.cj.library.dto.response.BookResponse;
import com.cj.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toEntity(BookRequest request);

    BookResponse toDto(Book book);

    List<BookResponse> toDtoList(List<Book> books);

    void updateEntityFromRequest(BookRequest request, @MappingTarget Book book);
}
