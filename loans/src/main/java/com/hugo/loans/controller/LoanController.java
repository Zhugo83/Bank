package com.hugo.loans.controller;

import com.hugo.loans.config.LoanConfig;
import com.hugo.loans.dto.CustomerDTO;
import com.hugo.loans.model.Loan;
import com.hugo.loans.model.Properties;
import com.hugo.loans.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService service;
    private final LoanConfig loanConfig;

    public LoanController(LoanService service, LoanConfig loanConfig) {
        this.service = service;
        this.loanConfig = loanConfig;
    }


    @GetMapping()
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = service.findAllExistingLoans();
        if (loans.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @PostMapping("/details/my-loans")
    public ResponseEntity<List<Loan>> getLoansDetails(@RequestBody CustomerDTO request) {
        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Loan> loans = service.findAllExistingLoansByCustomerId(request.getCustomerId());
        if (loans.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/details/properties")
    public ResponseEntity<Properties> getPropertiesDetails(){
        Properties properties = new Properties(
                loanConfig.getMsg(),
                loanConfig.getBuildVersion(),
                loanConfig.getMailDetails(),
                loanConfig.getActiveBranches()
        );
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PostMapping
    public Loan create(@RequestBody Loan loan){
        return service.create(loan);
    }
}
