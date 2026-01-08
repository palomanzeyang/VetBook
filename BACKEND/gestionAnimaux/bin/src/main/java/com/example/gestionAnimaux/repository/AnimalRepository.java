package com.example.gestionAnimaux.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.gestionAnimaux.entites.*;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String>{
	
		@Query("SELECT a FROM Animal a WHERE a.proprietaire.idProprietaire = :idProprietaire")
		List<Animal> findByProprietaire_IdProprietaire(String idProprietaire);
		
		List<Animal> findByVeterinaire_IdVeterinaire(String idVeterinaire);
		
		@Query("SELECT a FROM Animal a WHERE a.proprietaire.idProprietaire = :idProprietaire")
	    List<Animal> findIdAnimalAndProprietaireByIdProprietaire(String idProprietaire);
		
		 @Query("SELECT COUNT(a) FROM Animal a")
		  long countAllAnimals();
		 
		 public interface DossiermedicalRepository extends JpaRepository<Dossiermedical, String> {
		    List<Dossiermedical> findByAnimal_IdAnimal(String idAnimal);
		}

		public interface ConsultationRepository extends JpaRepository<Consultation, String> {
		    List<Consultation> findByDossiermedical_IdDossiermedical(String idDossier);
		}

		public interface VaccinRepository extends JpaRepository<Vaccin, String> {
		    List<Vaccin> findByDossiermedical_Animal_IdAnimal(String idAnimal);
		    List<Vaccin> findByDateRappelAfterAndDossiermedical_Animal_IdAnimal(LocalDateTime date, String idAnimal);
		}

		public interface TraitementPrescriptionRepository extends JpaRepository<TraitementPrescription, String> {
		    List<TraitementPrescription> findByConsultation_IdConsultation(String idConsultation);
		}

		public interface MaladieRepository extends JpaRepository<Maladie, String> {
		    List<Maladie> findByDossiermedical_Animal_IdAnimal(String idAnimal);
		}

}
