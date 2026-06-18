package com.hugo.accounts.controller;

import com.hugo.accounts.model.Customer;
import com.hugo.accounts.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    public final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAccount(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @GetMapping("/customer/{customer-id}")
    public ResponseEntity<Optional<Customer>> getAllAccountsByCustomer(
            @PathVariable("customer-id") Integer customerId
    ){
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }
}
