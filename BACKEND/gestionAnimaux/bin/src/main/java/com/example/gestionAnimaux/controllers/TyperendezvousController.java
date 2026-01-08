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

import com.example.gestionAnimaux.entites.TypeRendezvous;
import com.example.gestionAnimaux.service.TypeRendezvousService;

import jakarta.websocket.server.PathParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/type")
public class TyperendezvousController {
	
	@Autowired
	TypeRendezvousService typeRendezvousService;
	
	@PostMapping
    public ResponseEntity<?> save(@RequestBody TypeRendezvous e) 
	{	      
      
       TypeRendezvous res = typeRendezvousService.saveOrUpdate(e);
        if (res == null) {
            return new ResponseEntity<>("Échec de l'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

	
	@GetMapping
	public ResponseEntity<?> get()
	{
		return new ResponseEntity<>(typeRendezvousService.getAll(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathParam("IdTyperendezvous") String IdTyperendezvous)
	{
		TypeRendezvous res = typeRendezvousService.delete(IdTyperendezvous);
		if(res == null)
		{
			return new ResponseEntity<>("Echec d'enregistrement", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
