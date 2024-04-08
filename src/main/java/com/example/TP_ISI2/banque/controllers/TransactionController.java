package com.example.TP_ISI2.banque.controllers;

import com.example.TP_ISI2.banque.models.Transaction;
import com.example.TP_ISI2.banque.models.TransactionType;
import com.example.TP_ISI2.banque.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> processTransaction(@RequestBody Transaction transaction) {
        TransactionType type = transaction.getType();
        Long accountId = transaction.getCompteSourceId();
        Long sourceAccountId = transaction.getCompteSourceId();
        Long destinationAccountId = transaction.getCompteDestinationId();
        BigDecimal montant = transaction.getMontant();

        Transaction processedTransaction = null;

        if (type == TransactionType.VERSEMENT) {
            transactionService.makeVersement(accountId, montant);
        } else if (type == TransactionType.RETRAIT) {
            transactionService.makeRetrait(accountId, montant);
        } else if (type == TransactionType.VIREMENT) {
            transactionService.makeVirement(sourceAccountId, destinationAccountId, montant);
        }


        //  récupérer la dernière transaction enregistrée dans la base de données :
//        processedTransaction = transactionRepository.findFirstByOrderByIdDesc();

        // renvoyer directement la transaction qui vient d'être traitée :
        processedTransaction = transaction;

        // Renvoyer la transaction traitée avec le statut HTTP 200 OK si la transaction est effectuée avec succès
        return ResponseEntity.ok(processedTransaction);
    }

    // Point de terminaison pour récupérer les transactions par compte et période
    @GetMapping("/transaction")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountAndPeriod(
            @RequestParam Long accountId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        // Appelle la méthode du service pour récupérer les transactions
        List<Transaction> transactions = transactionService.getTransactionsByAccountIdAndPeriod(accountId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }


}




