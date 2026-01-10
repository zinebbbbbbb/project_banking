package com.example.demo.security;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Integer> {
    Optional<Utilisateurs> findByEmail(String email);
    Optional<Utilisateurs> findByAccount_AccountNumber(String accountNumber);
}