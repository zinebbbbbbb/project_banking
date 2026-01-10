package com.example.demo.Client;

import com.example.demo.Account.Account;
import com.example.demo.security.Utilisateurs;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private LocalDate birthday;

    // ML-related fields
    private BigDecimal limitBal;
    private Integer sex;
    private Integer education;
    private Integer marriage;
    private Integer age;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateurs utilisateur;


}