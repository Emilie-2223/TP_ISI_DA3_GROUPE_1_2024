package com.example.TP_ISI2.banque.controllers;

import com.example.TP_ISI2.banque.models.Transaction;
import com.example.TP_ISI2.banque.models.TransactionType;
import com.example.TP_ISI2.banque.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public void processTransaction(@RequestBody Transaction transaction) {
        TransactionType type = transaction.getType();
        Long accountId = transaction.getCompteSourceId();
        Long sourceAccountId = transaction.getCompteSourceId();
        Long destinationAccountId = transaction.getCompteDestinationId();
        BigDecimal montant = transaction.getMontant();

        if (type == TransactionType.VERSEMENT) {
            transactionService.makeVersement(accountId, montant);
        } else if (type == TransactionType.RETRAIT) {
            transactionService.makeRetrait(accountId, montant);
        } else if (type == TransactionType.VIREMENT) {
            transactionService.makeVirement(sourceAccountId, destinationAccountId, montant);
        }
    }
}



//if (type == TransactionType.VERSEMENT) {
//        if (accountId == null) {
//        throw new IllegalArgumentException("L'identifiant du compte est requis pour un versement.");
//            }
//                    transactionService.makeVersement(accountId, amount);
//        } else if (type == TransactionType.RETRAIT) {
//        if (accountId == null) {
//        throw new IllegalArgumentException("L'identifiant du compte est requis pour un retrait.");
//            }
//                    transactionService.makeRetrait(accountId, amount);
//        } else if (type == TransactionType.VIREMENT) {
//        if (sourceAccountId == null || destinationAccountId == null) {
//        throw new IllegalArgumentException("Les identifiants des comptes source et destination sont requis pour un virement.");
//            }
//                    transactionService.makeVirement(sourceAccountId, destinationAccountId, amount);
//        } else {
//                throw new IllegalArgumentException("Type de transaction non valide.");
//        }
//
