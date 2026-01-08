package com.example.gestionAnimaux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.gestionAnimaux.entites.*;
@Repository
public interface TraitementPrescriptionRepository extends JpaRepository<TraitementPrescription, String> {

	    List<TraitementPrescription> findByConsultation_IdConsultation(String idConsultation);

}
