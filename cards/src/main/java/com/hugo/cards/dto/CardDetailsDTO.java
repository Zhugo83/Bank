package com.hugo.cards.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
public class CardDetailsDTO {
    private String cardType;
    private String cardNumber;
    private Long totalLimit;
    private Long amountUsed;
    private Long availableAmount;
    private LocalDate createdDate;
}