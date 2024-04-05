package com.example.TP_ISI2.banque.controllers;

import com.example.TP_ISI2.banque.models.Transaction;
import com.example.TP_ISI2.banque.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint pour récupérer les transactions d'un compte pendant une période donnée
    @GetMapping("/transactions")
    public List<Transaction> getTransactionsByAccountAndPeriod(
            @RequestParam Long accountId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return transactionService.getTransactionsByAccountAndPeriod(accountId, startDate, endDate);
    }

    // Endpoint pour effectuer un versement sur un compte
    @PostMapping("/versement")
    public void makeVersement(
            @RequestParam Long accountId,
            @RequestParam BigDecimal amount) {
        transactionService.makeVersement(accountId, amount);
    }

    // Endpoint pour effectuer un retrait sur un compte
    @PostMapping("/retrait")
    public void makeRetrait(
            @RequestParam Long accountId,
            @RequestParam BigDecimal amount) {
        transactionService.makeRetrait(accountId, amount);
    }

    // Endpoint pour effectuer un virement d'un compte à un autre
    @PostMapping("/virement")
    public void makeVirement(
            @RequestParam Long sourceAccountId,
            @RequestParam Long destinationAccountId,
            @RequestParam BigDecimal amount) {
        transactionService.makeVirement(sourceAccountId, destinationAccountId, amount);
    }
}

