package com.example.gestionAnimaux.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.gestionAnimaux.entites.Veterinaire;

@Repository
public interface VeterinaireRepository extends JpaRepository<Veterinaire, String> {
   
   Veterinaire findByEmailVeterinaire(String emailVeterinaire);
    boolean existsByEmailVeterinaire(String emailVeterinaire);
}