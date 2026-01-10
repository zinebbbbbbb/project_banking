package com.example.demo.Transaction;

import com.example.demo.Account.Account;
import com.example.demo.Account.AccountRepository;
import com.example.demo.Client.Client;
import com.example.demo.Client.ClientRepository;
import com.example.demo.Kafka.TransferProducer;
import com.example.demo.security.Utilisateurs;
import com.example.demo.security.UtilisateursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UtilisateursRepository utilisateurRepository;
    private final TransferProducer transferProducer;

    @Transactional
    public Transaction withdraw(String email, BigDecimal amount, String description) {

        Utilisateurs user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = user.getAccount();

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction deposit(String email, BigDecimal amount, String description) {

        Utilisateurs user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = user.getAccount();

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction transfer(TransferRequest request, String email) {

        // Source user
        Utilisateurs sourceUser = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account sourceAccount = sourceUser.getAccount();

        // Target account
        Account targetAccount = accountRepository.findByAccountNumber(request.accountnbr())
                .orElseThrow(() -> new RuntimeException("Target account not found"));

        Utilisateurs targetUser = targetAccount.getUtilisateur();

        // 1️⃣ Withdraw (persisted)
        Transaction withdrawTx = withdraw(
                sourceUser.getEmail(),
                request.amount(),
                request.description()
        );

        // 2️⃣ Deposit
        deposit(
                targetUser.getEmail(),
                request.amount(),
                request.description()
        );

        // 3️⃣ Kafka event
        transferProducer.SendTransferConfirmation(
                new TransferConfirmation(
                        sourceUser.getEmail(),
                        targetUser.getEmail(),
                        request.amount(),
                        LocalDateTime.now(),
                        request.description(),
                        sourceAccount.getAccountNumber(),
                        targetAccount.getAccountNumber()
                )
        );

        return withdrawTx;
    }
}
