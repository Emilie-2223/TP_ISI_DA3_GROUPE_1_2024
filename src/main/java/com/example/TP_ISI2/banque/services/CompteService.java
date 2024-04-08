package com.example.TP_ISI2.banque.services;

import com.example.TP_ISI2.banque.models.Compte;
import com.example.TP_ISI2.banque.repositories.CompteRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.iban4j.IbanFormatException;
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

         //Générer un numéro de compte unique
        String numeroCompte = generateUniqueAccountNumber();

        // Initialiser le compte avec le numéro de compte généré
        compte.setNumero(numeroCompte);
        compte.setSolde(0); // Initialiser le solde à zéro lors de la création d'un compte

        return compteRepository.save(compte);
    }

    // Méthode pour générer un numéro de compte unique
    private String generateUniqueAccountNumber() {
        String generatedAccountNumber = null;
        boolean unique = false;
        while (!unique) {
            // Générer un numéro de compte avec iban4j
            try {
                Iban iban = new Iban.Builder()
                        .countryCode(CountryCode.FR)
                        .bankCode("00000") // Remplacer par le code banque approprié
                        .buildRandom();
                generatedAccountNumber = iban.toString();

                // Vérifier si le numéro de compte généré est unique
                if (!isAccountNumberExists(generatedAccountNumber)) {
                    unique = true;
                }
            } catch (IbanFormatException e) {
                e.printStackTrace(); // Gérer l'erreur de formatage si nécessaire
            }
        }
        return generatedAccountNumber;
    }

    // Méthode pour vérifier si un numéro de compte existe déjà dans la base de données
    private boolean isAccountNumberExists(String accountNumber) {
        return compteRepository.existsByNumero(accountNumber);
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
