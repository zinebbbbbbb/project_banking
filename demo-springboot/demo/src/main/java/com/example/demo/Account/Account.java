package com.example.demo.Account;

import com.example.demo.security.Utilisateurs;
import com.example.demo.Transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status = AccountStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonIgnore
    private Utilisateurs utilisateur;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore  // ← ADD THIS!
    private List<Transaction> transactions;
}