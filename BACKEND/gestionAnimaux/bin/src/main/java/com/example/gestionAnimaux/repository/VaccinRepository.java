package com.example.gestionAnimaux.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import com.example.gestionAnimaux.entites.Vaccin;

@Repository
public interface VaccinRepository extends JpaRepository<Vaccin, String> {
    List<Vaccin> findByDossiermedical_Animal_IdAnimal(String idAnimal);
    
    @Query("SELECT v FROM Vaccin v WHERE v.dateRappel > :date AND v.dossiermedical.animal.idAnimal = :idAnimal")
    List<Vaccin> findByDateRappelAfterAndDossiermedical_Animal_IdAnimal(
    		LocalDateTime date, 
            String idAnimal
        );
    
   
}