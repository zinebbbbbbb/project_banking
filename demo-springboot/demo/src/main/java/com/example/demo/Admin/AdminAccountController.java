package com.example.demo.Admin;

import com.example.demo.Account.AccountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/accounts")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") // only admins
public class AdminAccountController {

    private final AdminAccountService adminAccountService;

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        return ResponseEntity.ok(adminAccountService.getAllAccounts());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(adminAccountService.getAccount(accountId));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> updateAccount(
            @PathVariable Long accountId,
            @RequestBody AdminAccountUpdateDTO dto
    ) {
        return ResponseEntity.ok(adminAccountService.updateAccount(accountId, dto));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        adminAccountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}
