package com.hugo.accounts.controller;

import com.hugo.accounts.config.AccountConfig;
import com.hugo.accounts.dto.CustomerDTO;
import com.hugo.accounts.dto.CustomerDetails;
import com.hugo.accounts.model.Customer;
import com.hugo.accounts.model.Loan;
import com.hugo.accounts.model.Properties;
import com.hugo.accounts.model.Account;
import com.hugo.accounts.service.AccountService;
import com.hugo.accounts.service.LoanClientService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private static final String CUSTOMER_ID_NULL_OR_EMPTY = "Customer id is null or empty";
    private static final String ORDER_SERVICE = "customerDetailsService";
    private static final String ORDER_FALLBACK = "customerDetailsFallBack";

    @Value("${server.port}")
    private String serverPort;

    private final AccountConfig accountConfig;

    private final AccountService accountService;

    private final LoanClientService loanService;

    public AccountController(AccountConfig accountConfig, AccountService accountService, LoanClientService loanService) {
        this.accountConfig = accountConfig;
        this.accountService = accountService;
        this.loanService = loanService;
    }


    @PostMapping("/details/my-account")
    public ResponseEntity<List<Account>> getAccountsDetails(@RequestBody CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Account> accounts = accountService.getAllAccountsListFromCustomer(customerDTO.getCustomerId());
        if (accounts.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.findAllExistingAccounts();
        if (accounts.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping()
    public Account createAccount(@RequestBody Account account) {
        return accountService.createNewAccount(account);
    }

    @GetMapping("/details/properties")
    public ResponseEntity<Properties> getPropertiesDetails() {
        Properties properties = new Properties(
                accountConfig.getName(),
                accountConfig.getMsg(),
                accountConfig.getBuildVersion(),
                accountConfig.getMailDetails(),
                accountConfig.getActivesBranches()
        );
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = ORDER_FALLBACK)
    @PostMapping("/details/customer-details")
    public ResponseEntity<CustomerDetails> getCustomerDetails(@RequestBody CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Account> accounts = accountService.getAllAccountsListFromCustomer(customerDTO.getCustomerId());
        List<Loan> loans = loanService.getLoansList(customerDTO.getCustomerId());

        CustomerDetails customerDetails = CustomerDetails.builder()
                .accounts(accounts)
                .loans(loans)
                .build();
        return new ResponseEntity<>(customerDetails, HttpStatus.OK);
    }

    private ResponseEntity<CustomerDetails> customerDetailsFallBack(CustomerDTO dto, Throwable throwable) {
        List<Account> accounts = accountService.getAllAccountsListFromCustomer(dto.getCustomerId());

        CustomerDetails customerDetails = CustomerDetails.builder()
                .accounts(accounts)
                .build();
        return new ResponseEntity<>(customerDetails, HttpStatus.OK);
    }

    @GetMapping("/instance")
    public ResponseEntity<String> getServerInstance(){
        return ResponseEntity.ok("Server is running on port: " + serverPort);
    }

}