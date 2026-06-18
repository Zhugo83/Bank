package com.hugo.accounts.service;

import com.hugo.accounts.model.Account;
import com.hugo.accounts.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }
    
    public void editAccount(Account account) {
        accountRepository.save(account);
    }

    public void  deleteAccount(Account account) {
        accountRepository.delete(account);
    }
    
    public Optional<Account> findAllAccountsByCustomer(Integer id) {
        return accountRepository.findById(id);
    }

}
