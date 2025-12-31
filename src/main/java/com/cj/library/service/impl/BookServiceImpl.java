package com.cj.library.service.impl;

import com.cj.library.dto.request.BookRequest;
import com.cj.library.dto.response.BookResponse;
import com.cj.library.exception.ResourceNotFoundException;
import com.cj.library.mapper.BookMapper;
import com.cj.library.repository.BookRepository;
import com.cj.library.service.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAll() {
        return bookMapper.toDtoList(bookRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookResponse> getById(Long id) {
        return Optional.of(
                bookMapper.toDto(bookRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Book not found.")
                        ))
        );
    }

    @Override
    @Transactional
    public BookResponse create(BookRequest request) {
        return bookMapper.toDto(
                bookRepository.save(
                        bookMapper.toEntity(request)
                )
        );

//        Book bookCreated = Book.builder()
//                .isbn(request.getIsbn())
//                .title(request.getTitle())
//                .author(request.getAuthor())
//                .publisher(request.getPublisher())
//                .category(request.getCategory())
//                .publicationYear(request.getPublicationYear())
//                .totalCopies(request.getTotalCopies())
//                .availableCopies(request.getAvailableCopies())
//                .build();
//
//        bookRepository.save(bookCreated);
//
//        return bookMapper.toDto(bookCreated);
    }

    @Override
    @Transactional
    public BookResponse update(Long id, BookRequest request) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookMapper.updateEntityFromRequest(request, book);
                    return bookMapper.toDto(book);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

//        Book bookExists = bookRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
//
//        bookMapper.updateEntityFromRequest(request,bookExists);
//
//        return bookMapper.toDto(bookRepository.save(bookExists));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.findById(id)
                .ifPresentOrElse(book -> bookRepository.deleteById(id),
                        () -> new ResourceNotFoundException("Book not found"));
    }
}
