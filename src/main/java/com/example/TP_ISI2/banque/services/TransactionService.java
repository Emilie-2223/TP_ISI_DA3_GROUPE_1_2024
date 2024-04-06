package com.example.TP_ISI2.banque.services;

import com.example.TP_ISI2.banque.models.Compte;
import com.example.TP_ISI2.banque.models.Transaction;
import com.example.TP_ISI2.banque.models.TransactionType;
import com.example.TP_ISI2.banque.repositories.CompteRepository;
import com.example.TP_ISI2.banque.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void makeVersement(Long compteSourceId, BigDecimal montant) {
        Compte compte = compteRepository.findById(compteSourceId).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        double nouveauSolde = compte.getSolde() + montant.doubleValue(); // Ajout du montant versé
        compte.setSolde(nouveauSolde);
        compteRepository.save(compte);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.VERSEMENT);
        transaction.setCompteSourceId(compteSourceId);
        transaction.setMontant(montant);
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setDescription("Versement sur le compte " + compteSourceId);
        transactionRepository.save(transaction);
    }

    @Transactional
    public void makeRetrait(Long compteSourceId, BigDecimal montant) {
        Compte compte = compteRepository.findById(compteSourceId).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        double nouveauSolde = compte.getSolde() - montant.doubleValue(); // Retrait du montant
        if (nouveauSolde < 0) {
            throw new RuntimeException("Solde insuffisant");
        }
        compte.setSolde(nouveauSolde);
        compteRepository.save(compte);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.RETRAIT);
        transaction.setCompteSourceId(compteSourceId);
        transaction.setMontant(montant);
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setDescription("Retrait sur le compte " + compteSourceId);
        transactionRepository.save(transaction);
    }

    @Transactional
    public void makeVirement(Long sourceAccountId, Long destinationAccountId, BigDecimal amount) {
        Compte sourceCompte = compteRepository.findById(sourceAccountId).orElseThrow(() -> new RuntimeException("Compte source non trouvé"));
        Compte destinationCompte = compteRepository.findById(destinationAccountId).orElseThrow(() -> new RuntimeException("Compte destination non trouvé"));

        double soldeSource = sourceCompte.getSolde();
        double soldeDestination = destinationCompte.getSolde();

        if (soldeSource < amount.doubleValue()) {
            throw new RuntimeException("Solde insuffisant");
        }

        // Débiter le montant du virement du compte source
        double nouveauSoldeSource = soldeSource - amount.doubleValue();
        sourceCompte.setSolde(nouveauSoldeSource);

        // Créditer le montant du virement sur le compte de destination
        double nouveauSoldeDestination = soldeDestination + amount.doubleValue();
        destinationCompte.setSolde(nouveauSoldeDestination);

        // Enregistrer les modifications des comptes
        compteRepository.save(sourceCompte);
        compteRepository.save(destinationCompte);

        // Enregistrer la transaction dans la table transaction
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.VIREMENT);
        transaction.setCompteSourceId(sourceAccountId);
        transaction.setCompteDestinationId(destinationAccountId);
        transaction.setMontant(amount);
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setDescription("Virement de " + sourceAccountId + " vers " + destinationAccountId);
        transactionRepository.save(transaction);
    }
}
