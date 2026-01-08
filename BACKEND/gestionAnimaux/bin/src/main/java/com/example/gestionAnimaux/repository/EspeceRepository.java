package com.example.gestionAnimaux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.gestionAnimaux.entites.*;

@Repository
public interface EspeceRepository extends JpaRepository<Espece, String> {
	 @Query("SELECT COUNT(e) FROM Espece e")
	    long countAllEspeces();
	
}


