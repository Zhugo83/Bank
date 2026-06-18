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

    private Long customerId;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long accountNumber;
    private String accountType;
    private String bankAddress;
    private LocalDate createdDate;

}
