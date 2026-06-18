package com.hugo.loans.service;

import com.hugo.loans.model.Loan;
import com.hugo.loans.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void addLoan(Loan loan) {
        loanRepository.save(loan);
    }

    public Optional<Loan> findAllLoansByCustomer(Integer customerId) {
        return loanRepository.findById(customerId);
    }

    public void updateLoan(Loan loan) {
        loanRepository.save(loan);
    }

    public void deleteLoan(Loan loan) {
        loanRepository.delete(loan);
    }
}
