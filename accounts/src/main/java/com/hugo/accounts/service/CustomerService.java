package com.hugo.accounts.service;

import com.hugo.accounts.model.Customer;
import com.hugo.accounts.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void editAccount(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteAccount(Customer customer) {
        customerRepository.delete(customer);
    }

    public Optional<Customer> findCustomerById(Integer id) {
        return customerRepository.findById(id);
    }
}
