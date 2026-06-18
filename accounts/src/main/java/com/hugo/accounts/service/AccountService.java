package com.hugo.accounts.service;

import com.hugo.accounts.model.Account;
import com.hugo.accounts.repository.AccountRepository;
import com.hugo.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    public List<Account> getAllAccountsListFromCustomer(Long customerId) {
        return accountRepository.findAllAccountsByCustomerId(customerId);
    }

    public List<Account> findAllExistingAccounts() {
        return accountRepository.findAll();
    }

    public Account createNewAccount(Account account) {
        return accountRepository.save(account);
    }

    private boolean isAccountPresent(Integer accountNumber) {
        return accountRepository.existsById(accountNumber);
    }

    private Long generateAccountNumber() {
        long accountNumber;
        do {
            UUID uuid = UUID.randomUUID();
            accountNumber = (Math.abs(uuid.toString().hashCode() % 1000000000L));
        } while (isAccountPresent(Math.toIntExact(accountNumber)));
        return accountNumber;
    }
}
