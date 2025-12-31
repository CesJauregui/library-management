package com.cj.library.controller;

import com.cj.library.dto.request.BookRequest;
import com.cj.library.dto.response.BookResponse;
import com.cj.library.service.interfaces.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        log.info("GET /api/books - Getting all books");
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<Optional<BookResponse>> getBookById(@PathVariable Long id) {
        log.info("GET /api/books/{id} - Getting book: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<BookResponse> postBook(@Valid @RequestBody BookRequest request) {
        log.info("POST /api/books - Creating new book: {}", request.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<BookResponse> putBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        log.info("PUT /api/books/{id} - Updating book: {}", request.getTitle());
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("DELETE /api/books/{id} - Deleting book: {}", id);
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
