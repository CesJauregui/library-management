package com.cj.library.service.impl;

import com.cj.library.dto.request.LoanRequest;
import com.cj.library.dto.response.LoanResponse;
import com.cj.library.dto.response.MessageResponse;
import com.cj.library.entity.Book;
import com.cj.library.entity.Loan;
import com.cj.library.entity.User;
import com.cj.library.enums.StatusLoan;
import com.cj.library.exception.ResourceNotFoundException;
import com.cj.library.mapper.LoanMapper;
import com.cj.library.repository.BookRepository;
import com.cj.library.repository.LoanRepository;
import com.cj.library.repository.UserRepository;
import com.cj.library.service.interfaces.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getAllByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return loanMapper.toDtoList(loanRepository.findAllByUserId(user.getId()));
    }

    @Override
    @Transactional
    public LoanResponse newLoan(LoanRequest loanRequest) {
        userRepository.findById(loanRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        subtractBookCopy(loanRequest.getBookId());

        return loanMapper.toDto(
                loanRepository.save(
                        loanMapper.toEntity(loanRequest)
                )
        );
    }

    @Override
    @Transactional
    public LoanResponse renewLoan(Long id, LoanRequest loanRequest) {
        return loanRepository.findById(id)
                .map(loan -> {
                    loan.setDueDate(loanRequest.getDueDate());
                    loan.setReturnDate(loanRequest.getReturnDate());
                    loanRepository.save(loan);
                    return loanMapper.toDto(loan);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
    }

    @Override
    @Transactional
    public MessageResponse returnBook(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Loan not found")
        );

        addBookCopy(loan.getBook().getId());
        loan.setStatus(StatusLoan.RETURNED);

        return MessageResponse.builder()
                .message("Book returned successfully")
                .success(true)
                .build();
    }

    private void addBookCopy(Long id) {
        Book bookExists = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookExists.setAvailableCopies(bookExists.getAvailableCopies() + 1);
        bookRepository.save(bookExists);
    }

    private void subtractBookCopy(Long id) {
        Book bookExists = bookRepository.findById(id)
                .map(book -> {
                    if (book.getAvailableCopies() <= 0) throw new RuntimeException("No available copies");
                    return book;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookExists.setAvailableCopies(bookExists.getAvailableCopies() - 1);
        bookRepository.save(bookExists);
    }
}
