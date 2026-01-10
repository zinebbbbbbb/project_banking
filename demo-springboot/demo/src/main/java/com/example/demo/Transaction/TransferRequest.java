package com.example.demo.Transaction;

import com.example.demo.Account.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferRequest(
        BigDecimal amount,
        LocalDateTime timestamp,
        String description,
        String accountnbr
) {}