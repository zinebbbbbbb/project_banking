package com.example.demo.Account;

import com.example.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final JwtService jwtService;
    private final AccountService accountService;

    @PostMapping("/create")
    public AccountResponseDTO createAccount(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam AccountType type
    ) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);
        Account account = accountService.createAccount(email, type);
        return AccountResponseDTO.fromEntity(account);
    }

    @GetMapping("/me")
    public AccountResponseDTO getMyAccount(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");  // ← FIXED: Extract token first
        String email = jwtService.extractUsername(token);  // ← FIXED: Use JwtService
        Account account = accountService.getAccountByUserEmail(email);
        return AccountResponseDTO.fromEntity(account);  // ← FIXED: Return DTO
    }

    @GetMapping("/{accountId}")
    public AccountResponseDTO getAccount(@PathVariable Long accountId) {  // ← FIXED: Return DTO
        Account account = accountService.getAccount(accountId);
        return AccountResponseDTO.fromEntity(account);  // ← FIXED: Return DTO
    }
}