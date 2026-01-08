package com.example.gestionAnimaux.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestionAnimaux.dto.AnimalDetailsDto;
import com.example.gestionAnimaux.dto.AnimalDto;
import com.example.gestionAnimaux.dto.ConsultationDto;
import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.service.AnimauxService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/animal")
public class AnimalController {
	
	@Autowired
	AnimauxService animauxService;
	
	public AnimalController(AnimauxService animauxService)
	{
		this.animauxService = animauxService;
	}
	
	@PostMapping("/saveOrUpdate")
    public ResponseEntity<AnimalDto> saveOrUpdateAnimal(@RequestBody AnimalDto animalDto) {
        try {
            AnimalDto savedAnimal = animauxService.saveOrUpdate(animalDto);
            return new ResponseEntity<>(savedAnimal, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping("/all")
	public ResponseEntity<?> get()
	{
		return new ResponseEntity<>(animauxService.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathParam("idAnimal") String idAnimal)
	{
		Animal res = animauxService.delete(idAnimal);
		if(res == null)
		{
			return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>(res, HttpStatus.OK);
	}
	

    @GetMapping("/proprietaire/{idProprietaire}")
    public ResponseEntity<List<AnimalDto>> getAnimauxByProprietaire(@PathVariable String idProprietaire) {
        List<AnimalDto> animaux = animauxService.findByProprietaireId(idProprietaire);
        if (animaux.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Utilise NO_CONTENT au lieu de NOT_FOUND si la liste est vide
        }
        return new ResponseEntity<>(animaux, HttpStatus.OK);
    }
    
    @GetMapping("/veterinaire/{idVeterinaire}")
    public ResponseEntity<List<AnimalDto>> getVeterinaireByVeterinaire(@PathVariable String idVeterinaire) {
    	List<AnimalDto> a = animauxService.findByVetId(idVeterinaire);
    	if (a.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Utilise NO_CONTENT au lieu de NOT_FOUND si la liste est vide
        }
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
    
    @GetMapping("/count")
    public long getNombreTotalAnimaux() {
        return animauxService.getNombreTotalAnimaux();
    }
    @GetMapping("/{id}/consultations")
    public List<Consultation> getConsultations(@PathVariable String idConsultation) {
		return animauxService.getConsultationsByAnimal(idConsultation);
        
    }

  
    
    @GetMapping("/{id}/rappels")
    public List<Vaccin> getVaccinsRappel(@PathVariable("id") String idAnimal) {
        // Utilise la date actuelle pour les rappels futurs
        return animauxService.getRappelsVaccin(idAnimal, LocalDate.now());
    }
    

    @GetMapping("/{id}/maladies")
    public List<Maladie> getMaladies(@PathVariable String idMaladie) {
        return animauxService.getMaladiesByAnimal(idMaladie);
    }

    @GetMapping("/consultation/{id}/traitements")
    public List<TraitementPrescription> getTraitements(@PathVariable String idTraitement) {
        return animauxService.getTraitementsByConsultation(idTraitement);
    }
    
    @GetMapping("/{idAnimal}/details-complets")
    public AnimalDetailsDto getAnimalFullDetails(@PathVariable String idAnimal) {
        return animauxService.getAnimalDetails(idAnimal);
    }

}
