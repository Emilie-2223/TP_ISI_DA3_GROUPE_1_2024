package com.example.TP_ISI2.banque.repositories;


import com.example.TP_ISI2.banque.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


}

