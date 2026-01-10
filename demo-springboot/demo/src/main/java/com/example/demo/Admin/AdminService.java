package com.example.demo.Admin;
import com.example.demo.security.Role;
import com.example.demo.security.Utilisateurs;
import com.example.demo.security.UtilisateursRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final UtilisateursRepository userRepository;

    public AdminService(UtilisateursRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<Utilisateurs> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by id
    public Optional<Utilisateurs> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Promote a user to ADMIN
    @Transactional
    public Utilisateurs promoteToAdmin(Integer id) {
        Utilisateurs user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(Role.ADMIN);
        return user;
    }

    // Demote an admin to USER
    @Transactional
    public Utilisateurs demoteToUser(Integer id) {
        Utilisateurs user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(Role.USER);
        return user;
    }

    // Delete a user
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
