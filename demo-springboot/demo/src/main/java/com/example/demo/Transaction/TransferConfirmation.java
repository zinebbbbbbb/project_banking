package com.example.demo.Transaction;

import com.example.demo.Account.Account;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferConfirmation(
        String email,
        String mail,
        BigDecimal amount,
        LocalDateTime timestamp,

        String description,

        String sourceAccountNumber,
        String targetAccountNumber

) {
}
