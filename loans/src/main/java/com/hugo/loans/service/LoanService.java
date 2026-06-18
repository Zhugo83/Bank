package com.hugo.loans.service;

import com.hugo.loans.model.Loan;
import com.hugo.loans.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public List<Loan> findAllExistingLoans() {
        return repository.findAll();
    }

    public List<Loan> findAllExistingLoansByCustomerId(long customerId) {
        return repository.findAllByCustomerId(customerId);
    }

    public Loan create(Loan loan) {
        return repository.save(loan);
    }
}
