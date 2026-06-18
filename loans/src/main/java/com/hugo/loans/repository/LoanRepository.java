package com.hugo.loans.repository;

import com.hugo.loans.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findAllByCustomerId(long customerId);
    
}
