package com.example.gestionAnimaux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestionAnimaux.dto.AnimalDto;
import com.example.gestionAnimaux.dto.TraitementDto;
import com.example.gestionAnimaux.entites.Animal;
import com.example.gestionAnimaux.entites.TraitementPrescription;
import com.example.gestionAnimaux.service.TraitementService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/traitement")
public class TraitementController {
	
	@Autowired
	TraitementService traitementService;
	
	@PostMapping("/saveOrUpdate")
    public ResponseEntity<TraitementDto> saveOrUpdate(@RequestBody TraitementDto traitementDto) {
        try {
            TraitementDto save = traitementService.saveOrUpdate(traitementDto);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping("/all")
	public ResponseEntity<?> get()
	{
		return new ResponseEntity<>(traitementService.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathParam("idTraitement") String idTraitement)
	{
		TraitementPrescription res = traitementService.delete(idTraitement);
		if(res == null)
		{
			return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
