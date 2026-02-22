package com.revpay.service.interfaces;

import com.revpay.entity.Loan;

import java.util.List;

public interface LoanService {

    void applyLoan(Double amount, int months);

    void approveLoan(Long loanId);

    void repayEmi(Long loanId);

    List<Loan> myLoans();
}
