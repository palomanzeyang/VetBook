package com.example.gestionAnimaux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.gestionAnimaux.entites.*;
@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, String> {
	 List<Rendezvous> findByVeterinaire_IdVeterinaire(String idVeterinaire);
	    List<Rendezvous> findByAnimal_IdAnimal(String idAnimal);
	    @Query("SELECT COUNT(r) FROM Rendezvous r")
	    long countAllRendezvous();
	    
	    @Query("SELECT COUNT(r) FROM Rendezvous r WHERE r.animal.veterinaire.idVeterinaire = :idVeterinaire")
	    long countByVeterinaire(String idVeterinaire);
}
