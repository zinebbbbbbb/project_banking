package com.example.demo.Account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public Account createAccount(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam AccountType type
    ) {
        String email = authHeader.replace("Bearer ", "");
        return accountService.createAccount(email, type);
    }

    @GetMapping("/me")
    public Account getMyAccount(@RequestHeader("Authorization") String authHeader) {
        String email = authHeader.replace("Bearer ", "");
        return accountService.getAccountByUserEmail(email);
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable Long accountId) {
        return accountService.getAccount(accountId);
    }
}
