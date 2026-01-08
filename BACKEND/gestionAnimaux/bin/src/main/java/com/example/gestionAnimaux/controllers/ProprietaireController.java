package com.example.gestionAnimaux.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestionAnimaux.entites.Proprietaire;
import com.example.gestionAnimaux.service.ProprietaireService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/auth/proprietaire")
public class ProprietaireController {
	@Autowired
	ProprietaireService proprietaireService;
	
    public ProprietaireController(ProprietaireService proprietaireService) {
        this.proprietaireService = proprietaireService;
    }

    @PostMapping("/register")
    public ResponseEntity<Proprietaire> register(@RequestBody Proprietaire proprietaire) {
        return ResponseEntity.ok(proprietaireService.register(proprietaire));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String emailProprietaire, @RequestParam String mdpProprietaire) {
        Optional<Proprietaire> proprietaire = proprietaireService.login(emailProprietaire, mdpProprietaire);
        return proprietaire.isPresent() ? ResponseEntity.ok("Login successful") : ResponseEntity.status(400).body("Invalid credentials");
    }
    
	@GetMapping
	public ResponseEntity<?> get()
	{
		return new ResponseEntity<>(proprietaireService.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathParam("idProprietaire") String idProprietaire)
	{
		Proprietaire res = proprietaireService.delete(idProprietaire);
		if(res == null)
		{
			return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>(res, HttpStatus.OK);
	}


}
