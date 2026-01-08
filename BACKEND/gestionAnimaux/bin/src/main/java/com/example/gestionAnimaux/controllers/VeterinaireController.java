package com.example.gestionAnimaux.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestionAnimaux.entites.Veterinaire;
import com.example.gestionAnimaux.service.VeterinaireService;

import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth/veterinaire")
public class VeterinaireController {
	
	@Autowired
	VeterinaireService veterinaireService;
	
	public VeterinaireController(VeterinaireService veterinaireService) {
		this.veterinaireService = veterinaireService;
	}
//    @PostMapping("/register")
//    public ResponseEntity<Veterinaire> register(@RequestBody Veterinaire veterinaire) {
//        return ResponseEntity.ok(veterinaireService.register(veterinaire));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String emailVeterinaire, @RequestParam String mdpVeterinaire) {
//        Optional<Veterinaire> veterinaire = veterinaireService.login(emailVeterinaire, mdpVeterinaire);
//        return veterinaire.isPresent() ? ResponseEntity.ok("Login successful") : ResponseEntity.status(400).body("Invalid credentials");
//    }
//    
	@GetMapping
	public ResponseEntity<?> get()
	{
		return new ResponseEntity<>(veterinaireService.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathParam("idVeterinaire") String idVeterinaire)
	{
		Veterinaire res = veterinaireService.delete(idVeterinaire);
		if(res == null)
		{
			return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>(res, HttpStatus.OK);
	}

	
}
