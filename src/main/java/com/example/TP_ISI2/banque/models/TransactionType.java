package com.example.TP_ISI2.banque.models;

public enum TransactionType {
    VERSEMENT("Versement"),
    RETRAIT("Retrait"),
    VIREMENT("Virement");

    private final String label;

    TransactionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

