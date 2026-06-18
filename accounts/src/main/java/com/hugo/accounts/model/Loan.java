package com.hugo.accounts.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class Loan {

    private Long id;
    private Long customerId;
    private LocalDate startDate;
    private String loanType;
    private Long totalLoan;
    private Long amountPaid;
    private Long outStandingAmount;
    private LocalDate createDate;

}
