package com.example.gestionAnimaux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.gestionAnimaux.entites.*;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, String> {
	    List<Consultation> findByDossiermedical_IdDossiermedical(String idDossiermedical);

}
