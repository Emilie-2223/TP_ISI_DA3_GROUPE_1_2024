package com.example.TP_ISI2.banque.models;


import jakarta.persistence.*;


@Entity
@Table(name = "comptes")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String numero;
    @Enumerated(EnumType.STRING)
    private TypeCompte type;
    private String dateCreation;
    private double solde;
    @ManyToOne
    @JoinColumn(name = "client_id") // Nom de la colonne dans la table de la base de données
    private Client proprietaire;

    // Constructeurs

    public Compte() {
    }

    public Compte(String numero, TypeCompte type, String dateCreation, double solde, Client proprietaire) {
        this.numero = numero;
        this.type = type;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.proprietaire = proprietaire;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TypeCompte  getType() {
        return type;
    }

    public void setType(TypeCompte  type) {
        this.type = type;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Client getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }
}
