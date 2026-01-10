package com.example.demo.Admin;

import com.example.demo.Account.AccountStatus;
import com.example.demo.Account.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdminAccountUpdateDTO {
    private AccountStatus status;
    private AccountType type;
    private BigDecimal balance;
}