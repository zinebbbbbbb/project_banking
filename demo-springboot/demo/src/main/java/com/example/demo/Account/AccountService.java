package com.example.demo.Account;

import com.example.demo.security.Utilisateurs;
import com.example.demo.security.UtilisateursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UtilisateursRepository utilisateursRepository;

    public Account createAccount(String email, AccountType type) {
        Utilisateurs user = utilisateursRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if account already exists
        if (accountRepository.findByUtilisateur(user).isPresent()) {
            throw new RuntimeException("Account already exists for this user");
        }

        Account account = new Account();
        account.setUtilisateur(user);
        account.setAccountNumber("ACC-" + System.currentTimeMillis());
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setType(type);

        return accountRepository.save(account);
    }

    public Account getAccountByUserEmail(String email) {
        Utilisateurs user = utilisateursRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return accountRepository.findByUtilisateur(user)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
