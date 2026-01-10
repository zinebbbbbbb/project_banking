package com.example.demo.Client;

import com.example.demo.security.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUtilisateur(Utilisateurs utilisateur);
}