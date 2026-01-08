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
import com.example.gestionAnimaux.dto.ConsultationDto;
import com.example.gestionAnimaux.entites.Consultation;
import com.example.gestionAnimaux.service.ConsultationService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/consultation")
public class ConsultationController {
	
	private ConsultationService consultationService;
	
	public ConsultationController(ConsultationService consultationService) {
		// TODO Auto-generated constructor stub
		this.consultationService = consultationService;
	}
	
	 @PostMapping("/saveOrUpdate")
	    public ResponseEntity<ConsultationDto> saveOrUpdateConsultation(@RequestBody ConsultationDto consultationDto) {
	        try {
	            ConsultationDto savedConsultation = consultationService.saveOrUpdate(consultationDto);
	            return new ResponseEntity<>(savedConsultation, HttpStatus.OK); // 200 OK si tout est bien
	        } catch (RuntimeException e) {
	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // 400 Bad Request
	        }
	    }
	
	@GetMapping
	public ResponseEntity<?> get()
	{
		return new ResponseEntity<>(consultationService.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathParam("idAnimal") String idConsultation )
	{
		Consultation res = consultationService.delete(idConsultation );
		if(res == null)
		{
			return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
