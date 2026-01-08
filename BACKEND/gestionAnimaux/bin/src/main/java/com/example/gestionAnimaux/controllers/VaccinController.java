package com.example.gestionAnimaux.controllers;

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
import com.example.gestionAnimaux.dto.VaccinDto;
import com.example.gestionAnimaux.entites.Animal;
import com.example.gestionAnimaux.entites.Vaccin;
import com.example.gestionAnimaux.service.VaccinService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/vaccin")
public class VaccinController {
	
	private VaccinService vaccinService;
	
	public VaccinController(VaccinService vaccinService)
	{
		this.vaccinService = vaccinService;
	}
	
	@PostMapping("/save")
    public ResponseEntity<VaccinDto> createVaccin(@RequestBody VaccinDto dto) {
        try {
            VaccinDto saved = vaccinService.save(dto);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
	
	@GetMapping
	public ResponseEntity<?> get()
	{
		return new ResponseEntity<>(vaccinService.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathParam("idVaccin") String idVaccin)
	{
		Vaccin res = vaccinService.delete(idVaccin);
		if(res == null)
		{
			return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
