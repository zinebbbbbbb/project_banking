package com.example.demo.Notification;

import com.example.demo.Account.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferConfirmation(
        String senderEmail,
        String receiverEmail,
        BigDecimal amount,
        LocalDateTime timestamp,
        String description,
        String sourceAccountNumber,
        String targetAccountNumber
) {}
