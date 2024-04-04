package com.example.TP_ISI2.banque.services;

import com.example.TP_ISI2.banque.models.Client;
import com.example.TP_ISI2.banque.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Méthode pour créer un nouveau client
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Méthode pour récupérer tous les clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Méthode pour récupérer un client par son identifiant
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    // Méthode pour mettre à jour un client
    public Client updateClient(Long id, Client clientDetails) {
        // Recherche du client par son identifiant
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        // Enregistrement des modifications dans la base de données et retour du client mis à jour
        return clientRepository.save(client);
    }

    // Méthode pour supprimer un client
    public void deleteClient(Long id) {
        // Recherche du client par son identifiant
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        // Suppression du client de la base de données
        clientRepository.delete(client);
    }
}
