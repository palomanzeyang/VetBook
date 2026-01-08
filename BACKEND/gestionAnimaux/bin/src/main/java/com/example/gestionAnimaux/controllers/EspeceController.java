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

import com.example.gestionAnimaux.entites.Espece;
import com.example.gestionAnimaux.service.EspeceService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/espece")
public class EspeceController {
	
	@Autowired
	EspeceService especeService;
	
			@PostMapping
		    public ResponseEntity<?> save(@RequestBody Espece e) 
			{	      
		       
		        Espece res = especeService.saveOrUpdate(e);
		        if (res == null) {
		            return new ResponseEntity<>("Échec de l'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		        }
		        return new ResponseEntity<>(res, HttpStatus.OK);
		    }
		
			
			@GetMapping
			public ResponseEntity<?> get()
			{
				return new ResponseEntity<>(especeService.getAll(), HttpStatus.OK);
			}
			
			@DeleteMapping
			public ResponseEntity<?> delete(@PathParam("idEspece") String idEspece)
			{
				Espece res = especeService.delete(idEspece);
				if(res == null)
				{
					return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
				}
				 return new ResponseEntity<>(res, HttpStatus.OK);
			}
			
			 @GetMapping("/count")
			    public long getNombreTotalEspeces() {
			        return especeService.getNombreTotalEspeces();
			    }
}
