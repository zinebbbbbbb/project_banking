package com.example.demo.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class AccountResponseDTO {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private AccountStatus status;
    private AccountType type;
}