package com.example.TP_ISI2.banque.controllers;



import com.example.TP_ISI2.banque.models.Compte;
import com.example.TP_ISI2.banque.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    // Endpoint pour créer un nouveau compte
    @PostMapping
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte createdCompte = compteService.createCompte(compte);
        return new ResponseEntity<>(createdCompte, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer tous les comptes
    @GetMapping
    public ResponseEntity<List<Compte>> getAllComptes() {
        List<Compte> comptes = compteService.getAllComptes();
        return new ResponseEntity<>(comptes, HttpStatus.OK);
    }

    // Endpoint pour récupérer un compte par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Compte> getCompteById(@PathVariable Long id) {
        Compte compte = compteService.getCompteById(id);
        if (compte != null) {
            return new ResponseEntity<>(compte, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
