package com.hugo.accounts.client;

import com.hugo.accounts.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("ms-loans")
public interface LoanFeignClient {
    @PostMapping( "/loans/details/my-loans")
    List<Loan> getLoansDetails(@RequestBody Long customerId);

}