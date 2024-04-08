package com.example.TP_ISI2.banque.models;

import com.example.TP_ISI2.banque.validators.ValidEmail;
import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String dateNaissance;
    private String sexe;
    private String adresse;
    private long numeroTelephone;
    @NotEmpty
    @Email
    @ValidEmail
    private String courriel;
    private String nationalite;

    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL)
    private List<Compte> comptes;

    // Constructeurs

    public  Client() {
    }

    public Client(String nom, String prenom, String dateNaissance, String sexe, String adresse, long numeroTelephone, String courriel, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
        this.courriel = courriel;
        this.nationalite = nationalite;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public long getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(long numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }
}

