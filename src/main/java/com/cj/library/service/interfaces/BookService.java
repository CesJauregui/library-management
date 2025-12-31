package com.cj.library.service.interfaces;

import com.cj.library.dto.request.BookRequest;
import com.cj.library.dto.response.BookResponse;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookResponse> getAll();
    Optional<BookResponse> getById(Long id);
    BookResponse create(BookRequest request);
    BookResponse update(Long id, BookRequest request);
    void delete(Long id);
}
