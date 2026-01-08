package com.example.gestionAnimaux.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestionAnimaux.entites.*;
@Repository
public interface ProprietaireRepository extends JpaRepository<Proprietaire, String>  {
	Optional<Proprietaire> findByEmailProprietaire(String emailProprietaire);
	boolean existsByEmailProprietaire(String emailProprietaire);

}
