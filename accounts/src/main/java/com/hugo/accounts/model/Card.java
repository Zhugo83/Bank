package com.hugo.accounts.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class Card {

    private Long cardId;
    private Long customerId;
    private Long cardNumber;
    private Long totalLimit;
    private Long amountUsed;
    private Long AvailableAmount;
    private LocalDate date;

}
