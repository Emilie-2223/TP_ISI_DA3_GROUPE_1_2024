package com.example.TP_ISI2.banque.repositories;

import com.example.TP_ISI2.banque.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Méthode pour récupérer les transactions d'un compte pendant une période donnée
    List<Transaction> findByCompteSourceIdAndDateTransactionBetween(Long accountId, LocalDateTime startDate, LocalDateTime endDate);
}

