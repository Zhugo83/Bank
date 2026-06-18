package com.hugo.accounts.repository;

import com.hugo.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllAccountsByCustomerId(Long customerId);

}
