package com.hugo.accounts.controller;

import com.hugo.accounts.config.AccountConfig;
import com.hugo.accounts.dto.Properties;
import com.hugo.accounts.model.Account;
import com.hugo.accounts.service.AccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private static final String CUSTOMER_ID_NULL_OR_EMPTY = "Customer id is null or empty";
    private static final String ORDER_SERVICE = "customerDetailsService";
    private static final String ORDER_FALLBACK = "customerDetailsFallBack";

    private final AccountConfig config;
    private final AccountService accountService;

    public AccountController(AccountConfig config, AccountService accountService) {
        this.config = config;
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAccount(@RequestBody Account account) {
        accountService.addAccount(account);
    }

    @GetMapping("/account/{customer-id}")
    public ResponseEntity<Optional<Account>> getAllAccountsByCustomer(
            @PathVariable("customer-id") Integer customerId
    ){
        return ResponseEntity.ok(accountService.findAllAccountsByCustomer(customerId));
    }

    @GetMapping("/details/properties")
    public ResponseEntity<Properties> getProperties() {
        Properties properties = new Properties(
                config.getName(),
                config.getMsg(),
                config.getBuildVersion(),
                config.getMailDetails(),
                config.getActivesBranches()
        );

        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

}