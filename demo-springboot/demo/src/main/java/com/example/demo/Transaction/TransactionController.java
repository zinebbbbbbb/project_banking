package com.example.demo.Transaction;

import com.example.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final JwtService jwtService;

    @PostMapping("/withdraw")
    public Transaction withdraw(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody TransactionRequestDTO dto
    ) {
        // extract email from JWT (remove "Bearer " if present)
        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);

        return transactionService.withdraw(email, dto.getAmount(), dto.getDescription());
    }

    @PostMapping("/deposit")
    public Transaction deposit(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody TransactionRequestDTO dto
    ) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);

        return transactionService.deposit(email, dto.getAmount(), dto.getDescription());
    }


    @PostMapping("/transfer")
    public Transaction transfer(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody TransferRequest request
    ) {

        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);

        return transactionService.transfer(request, email);
    }
}
