package com.hugo.loans.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer customerId;
    private LocalDate startDate;
    private LoanType loanType;
    private Long totalLoan;
    private Long amountPaid;
    private Long outStandingAmount;
    private LocalDate createDate;

}
