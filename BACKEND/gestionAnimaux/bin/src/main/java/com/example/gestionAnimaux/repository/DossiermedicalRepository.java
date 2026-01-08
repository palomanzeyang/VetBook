package com.example.gestionAnimaux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.gestionAnimaux.entites.*;


@Repository
public interface DossiermedicalRepository extends JpaRepository<Dossiermedical, String> {
	List<Dossiermedical> findByAnimal_IdAnimal(String idAnimal);
	
}

