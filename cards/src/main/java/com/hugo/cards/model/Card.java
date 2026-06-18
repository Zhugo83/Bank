package com.hugo.cards.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer cardId;
    private Integer customerId;
    private Long cardNumber;
    private Long totalLimit;
    private Long amountUsed;
    private Long AvailableAmount;
    private LocalDate date;

}
