package com.example.demo.Admin;

import com.example.demo.Account.Account;
import com.example.demo.Account.AccountRepository;
import com.example.demo.Account.AccountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAccountService {

    private final AccountRepository accountRepository;

    // Get all accounts
    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // Get account by id
    public AccountResponseDTO getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return mapToResponseDTO(account);
    }

    // Update account (status, type, balance)
    public AccountResponseDTO updateAccount(Long accountId, AdminAccountUpdateDTO dto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (dto.getStatus() != null) account.setStatus(dto.getStatus());
        if (dto.getType() != null) account.setType(dto.getType());
        if (dto.getBalance() != null) account.setBalance(dto.getBalance());

        accountRepository.save(account);
        return mapToResponseDTO(account);
    }

    // Delete account
    public void deleteAccount(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new RuntimeException("Account not found");
        }
        accountRepository.deleteById(accountId);
    }

    private AccountResponseDTO mapToResponseDTO(Account account) {
        return AccountResponseDTO.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .status(account.getStatus())
                .type(account.getType())

                .build();
    }
}
