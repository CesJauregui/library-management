package com.cj.library.service.interfaces;

import com.cj.library.dto.request.LoanRequest;
import com.cj.library.dto.response.LoanResponse;
import com.cj.library.dto.response.MessageResponse;

import java.util.List;

public interface LoanService {
    List<LoanResponse> getAllByUser();

    LoanResponse newLoan(LoanRequest loanRequest);

    LoanResponse renewLoan(Long id, LoanRequest loanRequest);

    MessageResponse returnBook(Long id);
}
