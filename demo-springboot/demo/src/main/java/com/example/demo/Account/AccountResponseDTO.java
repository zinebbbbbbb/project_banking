package com.example.demo.Account;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor  // ← ADD THIS
@AllArgsConstructor
public class AccountResponseDTO {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private AccountStatus status;
    private AccountType type;

    public static AccountResponseDTO fromEntity(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBalance(account.getBalance());
        dto.setStatus(account.getStatus());
        dto.setType(account.getType());
        return dto;
    }
}