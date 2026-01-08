package com.example.gestionAnimaux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestionAnimaux.dto.MaladieDto;
import com.example.gestionAnimaux.entites.Maladie;
import com.example.gestionAnimaux.service.MaladieService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/maladie")
public class MaladieController {
	
	@Autowired
	private MaladieService maladieService;
	
	@PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MaladieDto m) 
	{	      
       
        MaladieDto res = maladieService.saveOrUpdate(m);
        if (res == null) {
            return new ResponseEntity<>("Échec de l'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

	
	@GetMapping
	public ResponseEntity<?> get()
	{
		return new ResponseEntity<>(maladieService.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathParam("idMaladie") String idMaladie)
	{
		Maladie res = maladieService.delete(idMaladie);
		if(res == null)
		{
			return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
}
