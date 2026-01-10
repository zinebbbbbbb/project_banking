package com.example.demo.Account;

import com.example.demo.security.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUtilisateur(Utilisateurs utilisateur);
    Optional<Account> findByAccountNumber(String accountNumber);
}