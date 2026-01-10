package com.example.demo.Transaction;
import com.example.demo.Account.Account;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // DEPOSIT, WITHDRAW, TRANSFER

    private BigDecimal amount;
    private LocalDateTime timestamp;

    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private Integer pay0;
    private Integer pay2;
    private Integer pay3;
    private Integer pay4;
    private Integer pay5;
    private Integer pay6;

    private BigDecimal billAmt1;
    private BigDecimal billAmt2;
    private BigDecimal billAmt3;
    private BigDecimal billAmt4;
    private BigDecimal billAmt5;
    private BigDecimal billAmt6;

    private BigDecimal payAmt1;
    private BigDecimal payAmt2;
    private BigDecimal payAmt3;
    private BigDecimal payAmt4;
    private BigDecimal payAmt5;
    private BigDecimal payAmt6;

    private Boolean defaultNextMonth;

    private Double defaultProbability;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
