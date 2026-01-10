package com.example.demo.Admin;

import com.example.demo.security.Utilisateurs;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Only admins can access these endpoints
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<Utilisateurs>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<Utilisateurs> getUser(@PathVariable Integer id) {
        return adminService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/promote")
    public ResponseEntity<Utilisateurs> promoteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(adminService.promoteToAdmin(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/demote")
    public ResponseEntity<Utilisateurs> demoteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(adminService.demoteToUser(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
