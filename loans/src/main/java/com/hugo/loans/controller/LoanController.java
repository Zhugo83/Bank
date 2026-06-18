package com.hugo.loans.controller;

import com.hugo.loans.model.Loan;
import com.hugo.loans.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAccount(@RequestBody Loan loan) {
        loanService.addLoan(loan);
    }

    @GetMapping("/loan/{customer-id}")
    public ResponseEntity<Optional<Loan>> getAllAccountsByCustomer(
            @PathVariable("customer-id") Integer customerId
    ){
        return ResponseEntity.ok(loanService.findAllLoansByCustomer(customerId));
    }
}
