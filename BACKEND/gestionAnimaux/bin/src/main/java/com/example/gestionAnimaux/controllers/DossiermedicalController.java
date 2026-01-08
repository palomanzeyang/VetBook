package com.example.gestionAnimaux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.gestionAnimaux.dto.DossierMedicalDto;
import com.example.gestionAnimaux.entites.Dossiermedical;
import com.example.gestionAnimaux.service.DossierMedicalService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/dossiermedical")
public class DossiermedicalController {
	
	@Autowired
	private DossierMedicalService dossierMedicalService;
	
	@PostMapping("/saveOrUpdate")

	public ResponseEntity<?> create(@RequestBody DossierMedicalDto dto) {
	    try {
	        DossierMedicalDto saved = dossierMedicalService.saveOrUpdate(dto);
	        return ResponseEntity.ok(saved);
	    } catch (RuntimeException e) {
	        String errorMessage = e.getMessage();

	        if (errorMessage != null) {
	            if (errorMessage.contains("Animal introuvable")) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("Erreur : l'animal spécifié est introuvable.");
	            } else if (errorMessage.contains("ID de l'animal manquant")) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                        .body("Erreur : vous devez spécifier l'ID de l'animal.");
	            } else if (errorMessage.contains("existe déjà pour cet animal")) {
	                return ResponseEntity.status(HttpStatus.CONFLICT)
	                        .body("Erreur : un dossier médical existe déjà pour cet animal.");
	            }
	        }

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Erreur lors de l'enregistrement : un dossier médical existe déjà pour cet animal. ");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Erreur de format : assurez-vous que la date est au format 'yyyy-MM-dd'.");
	    }
	}

	
	@GetMapping
	public ResponseEntity<?> get()
	{
		return new ResponseEntity<>(dossierMedicalService.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathParam("idDossiermedical") String idDossiermedical)
	{
		Dossiermedical res = dossierMedicalService.delete(idDossiermedical);
		if(res == null)
		{
			return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
