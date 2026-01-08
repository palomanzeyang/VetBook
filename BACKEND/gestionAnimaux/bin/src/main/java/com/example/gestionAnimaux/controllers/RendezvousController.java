package com.example.gestionAnimaux.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestionAnimaux.dto.RendezvousDto;
import com.example.gestionAnimaux.entites.Rendezvous;
import com.example.gestionAnimaux.service.RendezvousService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/rendezvous")
public class RendezvousController {
	
	@Autowired
	private RendezvousService rendezvousService;
	
	@PostMapping("/save")
	public ResponseEntity<?> createrdv(@RequestBody RendezvousDto dto) {
	    // Vérifications manuelles
	    if (dto.getDateRendezvous() == null) {
	        return ResponseEntity.badRequest().body("La date du rendez-vous est obligatoire.");
	    }
	    if (dto.getObjet() == null || dto.getObjet().trim().isEmpty()) {
	        return ResponseEntity.badRequest().body("L'objet du rendez-vous est obligatoire.");
	    }
	    if (dto.getStatut() == null || dto.getStatut().trim().isEmpty()) {
	        return ResponseEntity.badRequest().body("Le statut est obligatoire.");
	    }
	    if (dto.getIdAnimal() == null || dto.getIdAnimal().trim().isEmpty()) {
	        return ResponseEntity.badRequest().body("L'ID de l'animal est obligatoire.");
	    }
	    if (dto.getIdTyperendezvous() == null || dto.getIdTyperendezvous().trim().isEmpty()) {
	        return ResponseEntity.badRequest().body("L'ID du type de rendez-vous est obligatoire.");
	    }
	    if (dto.getIdVeterinaire() == null || dto.getIdVeterinaire().trim().isEmpty()) {
	        return ResponseEntity.badRequest().body("L'ID du vétérinaire est obligatoire.");
	    }

	    try {
	        RendezvousDto saved = rendezvousService.saveOrUpdate(dto);
	        return ResponseEntity.ok(saved);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body("Erreur lors de l'enregistrement : " + e.getMessage());
	    }
	}
	
	 @GetMapping("/veterinaire/{idVeterinaire}")
	    public ResponseEntity<List<RendezvousDto>> findByVeterinaireId(@PathVariable String idVeterinaire) {
	        List<RendezvousDto> rendezvousList = rendezvousService.findByVeterinaireId(idVeterinaire);
	        return ResponseEntity.ok(rendezvousList);
	    }

	    @GetMapping("/proprietaire/{idProprietaire}")
	    public ResponseEntity<List<RendezvousDto>> findByProprietaireId(@PathVariable String idProprietaire) {
	        List<RendezvousDto> rendezvousList = rendezvousService.findByProprietaireId(idProprietaire);
	        return ResponseEntity.ok(rendezvousList);
	    }

	
	@GetMapping
	public ResponseEntity<?> get()
	{
		return new ResponseEntity<>(rendezvousService.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathParam("idRendezvous") String idRendezvous)
	{
		Rendezvous res = rendezvousService.delete(idRendezvous);
		if(res == null)
		{
			return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	 @GetMapping("/count")
	    public long getNombreTotalRendezvous() {
	        return rendezvousService.getNombreTotalRendezvous();
	    }
	 
	 @GetMapping("/count/veterinaire/{idVeterinaire}")
	    public long getRendezvousCountByVeterinaire(@PathVariable String idVeterinaire) {
	        return rendezvousService.getNombreRendezvousByVeterinaire(idVeterinaire);
	    }

}
