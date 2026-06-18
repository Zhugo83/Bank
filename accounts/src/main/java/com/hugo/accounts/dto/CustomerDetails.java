package com.hugo.accounts.dto;

import com.hugo.accounts.model.Account;
import com.hugo.accounts.model.Card;
import com.hugo.accounts.model.Loan;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {
    List<Account> accounts;
    List<Loan> loans;
    List<Card> cards;
}