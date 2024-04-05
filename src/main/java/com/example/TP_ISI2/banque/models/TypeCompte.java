package com.example.TP_ISI2.banque.models;

public enum TypeCompte {
    EPARGNE("Epargne"),
    COURANT("Courant");

    private final String label;

    TypeCompte(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}


