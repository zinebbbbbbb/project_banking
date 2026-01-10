package com.example.demo.Transaction;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionRequestDTO {

    private BigDecimal amount;
    private String description;
}