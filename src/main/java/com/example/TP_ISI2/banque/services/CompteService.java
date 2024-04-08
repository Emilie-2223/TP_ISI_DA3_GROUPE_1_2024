package com.example.TP_ISI2.banque.services;

import com.example.TP_ISI2.banque.models.Compte;
import com.example.TP_ISI2.banque.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;

    // Méthode pour créer un nouveau compte avec un solde initialisé à zéro
    public Compte createCompte(Compte compte) {
        compte.setSolde(0); // Initialiser le solde à zéro lors de la création d'un compte
        return compteRepository.save(compte);
    }

    // Méthode pour récupérer tous les comptes
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    // Méthode pour récupérer un compte par  son ID
    public Compte getCompteById(Long id) {
        Optional<Compte> optionalCompte = compteRepository.findById(id);
        return optionalCompte.orElse(null);
    }


    // Méthode pour supprimer un compte
    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }
}
