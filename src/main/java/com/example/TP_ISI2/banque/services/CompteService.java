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

    // Méthode pour récupérer un compte par son ID
    public Compte getCompteById(Long id) {
        Optional<Compte> optionalCompte = compteRepository.findById(id);
        return optionalCompte.orElse(null);
    }

//    // Méthode pour effectuer un versement sur un compte
//    public void faireVersement(Compte compte, double montant) {
//        if (compte != null) {
//            compte.setSolde(compte.getSolde() + montant);
//            compteRepository.save(compte); // Enregistrer les modifications du compte dans la base de données
//        } else {
//            // Gérer le cas où le compte n'existe pas
//            throw new RuntimeException("Le compte n'existe pas.");
//        }
//    }
//
//    // Méthode pour effectuer un retrait sur un compte si le solde le permet
//    public void faireRetrait(Compte compte, double montant) {
//        if (compte != null) {
//            if (compte.getSolde() >= montant) {
//                compte.setSolde(compte.getSolde() - montant);
//                compteRepository.save(compte); // Enregistrer les modifications du compte dans la base de données
//            } else {
//                // Gérer le cas où le solde est insuffisant pour le retrait
//                throw new RuntimeException("Solde insuffisant pour effectuer le retrait.");
//            }
//        } else {
//            // Gérer le cas où le compte n'existe pas
//            throw new RuntimeException("Le compte n'existe pas.");
//        }
//    }
//
//    // Méthode pour effectuer un virement d'un compte à un autre
//    public void faireVirement(Compte compteSource, Compte compteDestination, double montant) {
//        if (compteSource != null && compteDestination != null) {
//            if (compteSource.getSolde() >= montant) {
//                compteSource.setSolde(compteSource.getSolde() - montant);
//                compteDestination.setSolde(compteDestination.getSolde() + montant);
//                compteRepository.save(compteSource); // Enregistrer les modifications du compte source dans la base de données
//                compteRepository.save(compteDestination); // Enregistrer les modifications du compte destination dans la base de données
//            } else {
//                // Gérer le cas où le solde du compte source est insuffisant pour le virement
//                throw new RuntimeException("Solde insuffisant pour effectuer le virement.");
//            }
//        } else {
//            // Gérer le cas où l'un des comptes n'existe pas
//            throw new RuntimeException("L'un des comptes n'existe pas.");
//        }
//    }

    // Méthode pour supprimer un compte
    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }
}
