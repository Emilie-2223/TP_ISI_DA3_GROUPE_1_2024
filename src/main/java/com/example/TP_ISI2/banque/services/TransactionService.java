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
import java.util.List;

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
        transaction.setDescription("Virement du compte " + sourceAccountId + " vers le compte" + destinationAccountId);
        transactionRepository.save(transaction);
    }


    // Méthode pour récupérer les transactions d'un compte pendant une période donnée


    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByAccountIdAndPeriod(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
        // Utilise la méthode existante du repository pour rechercher les transactions
        List<Transaction> transactions = transactionRepository.findByCompteSourceIdAndDateTransactionBetween(accountId, startDate, endDate);
        // Retournez les transactions sans effectuer de traitement supplémentaire
        return transactions;
    }












































//    @Transactional(readOnly = true)
//    public List<Transaction> getTransactionsByAccountIdAndPeriod(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
//        List<Transaction> transactions = transactionRepository.findByCompteSourceIdAndDateTransactionBetween(accountId, startDate, endDate);
//        // Ajoutez des logs pour imprimer les transactions récupérées
//        transactions.forEach(transaction -> {
////            System.out.println("Transaction ID: " + transaction.getId());
//            System.out.println("Compte Source ID: " + transaction.getCompteSourceId());
//            System.out.println("Compte Destination ID: " + transaction.getCompteDestinationId());
//            System.out.println("Montant: " + transaction.getMontant());
//            System.out.println("Type: " + transaction.getType());
//            // Ajoutez d'autres informations si nécessaire
//        });
//        return transactions;
//    }


}
