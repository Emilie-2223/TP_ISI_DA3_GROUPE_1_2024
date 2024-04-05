package com.example.TP_ISI2.banque.services;

import com.example.TP_ISI2.banque.models.Compte;
import com.example.TP_ISI2.banque.models.Transaction;
import com.example.TP_ISI2.banque.models.TransactionType;
import com.example.TP_ISI2.banque.repositories.CompteRepository;
import com.example.TP_ISI2.banque.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CompteRepository compteRepository;

    // Méthode pour récupérer les transactions d'un compte pendant une période donnée
    public List<Transaction> getTransactionsByAccountAndPeriod(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByCompteSourceIdAndDateTransactionBetween(accountId, startDate, endDate);
    }

    // Méthode pour effectuer un versement sur un compte
    public void makeVersement(Long accountId, BigDecimal amount) {
        Compte compte = compteRepository.findById(accountId).orElse(null);
        if (compte != null) {
            // Mettre à jour le solde du compte
            compte.setSolde(compte.getSolde());
            compteRepository.save(compte);

            // Enregistrer la transaction
            Transaction transaction = new Transaction();
            transaction.setType(TransactionType.VERSEMENT);
            transaction.setCompteSourceId(accountId);
            transaction.setMontant(amount);
            transaction.setDateTransaction(LocalDateTime.now());
            transactionRepository.save(transaction);
        }
    }

    // Méthode pour effectuer un retrait sur un compte
    public void makeRetrait(Long accountId, BigDecimal amount) {
        Compte compte = compteRepository.findById(accountId).orElse(null);
        if (compte != null && compte.getSolde() >= 0) {
            // Mettre à jour le solde du compte
            compte.setSolde(compte.getSolde());
            compteRepository.save(compte);

            // Enregistrer la transaction
            Transaction transaction = new Transaction();
            transaction.setType(TransactionType.RETRAIT);
            transaction.setCompteSourceId(accountId);
            transaction.setMontant(amount);
            transaction.setDateTransaction(LocalDateTime.now());
            transactionRepository.save(transaction);
        }
    }

    // Méthode pour effectuer un virement d'un compte à un autre
    public void makeVirement(Long sourceAccountId, Long destinationAccountId, BigDecimal amount) {
        Compte sourceCompte = compteRepository.findById(sourceAccountId).orElse(null);
        Compte destinationCompte = compteRepository.findById(destinationAccountId).orElse(null);

        if (sourceCompte != null && destinationCompte != null && sourceCompte.getSolde() >= 0) {
            // Mettre à jour les soldes des comptes source et destination
            sourceCompte.setSolde(sourceCompte.getSolde());
            destinationCompte.setSolde(destinationCompte.getSolde());

            compteRepository.save(sourceCompte);
            compteRepository.save(destinationCompte);

            // Enregistrer la transaction
            Transaction transaction = new Transaction();
            transaction.setType(TransactionType.VIREMENT);
            transaction.setCompteSourceId(sourceAccountId);
            transaction.setCompteDestinationId(destinationAccountId);
            transaction.setMontant(amount);
            transaction.setDateTransaction(LocalDateTime.now());
            transactionRepository.save(transaction);
        }
    }
}

