package com.hugo.accounts.service;

import com.hugo.accounts.client.LoanFeignClient;
import com.hugo.accounts.model.Loan;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanClientService {

    private final LoanFeignClient feignClient;
    private static final String RETRY_ORDER_SERVICE = "retryForCustomerDetails";
    private static final String ORDER_FALLBACK = "fallbackForLoans";


    @Retry(name = RETRY_ORDER_SERVICE, fallbackMethod = ORDER_FALLBACK)
    public List<Loan> getLoansList(Long customerId){
        return feignClient.getLoansDetails(customerId);
    }

    private List<Loan> fallbackForLoans(Long customerId, Throwable throwable) {
        return Collections.emptyList(); // Retourner une liste vide en cas d'échec
    }
}