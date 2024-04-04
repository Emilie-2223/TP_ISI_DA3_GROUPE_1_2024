package com.example.TP_ISI2.banque.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderAccountId; // Identifiant du compte expéditeur
    private Long receiverAccountId; // Identifiant du compte destinataire

    private String description;
    private double amount;
    private Date transactionDate;

    // Constructeurs
    public Transaction() {
    }

    public Transaction(Long senderAccountId, Long receiverAccountId, String description, double amount, Date transactionDate) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.description = description;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
