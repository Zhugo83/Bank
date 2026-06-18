package com.hugo.accounts.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer customerId;
    private Integer accountNumber;
    private AccountType accountType;
    private String bankAddress;
    private LocalDate createdDate;

}
