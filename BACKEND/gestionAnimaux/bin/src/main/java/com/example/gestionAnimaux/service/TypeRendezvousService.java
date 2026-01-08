package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.repository.*;
@Service
public class TypeRendezvousService {

    @Autowired
    private TypeRendezvousRepository typeRendezvousRepository;

    public TypeRendezvous saveOrUpdate(TypeRendezvous typeRendezvous) {
    	if(typeRendezvous.getIdTyperendezvous() == null || typeRendezvous.getIdTyperendezvous().isEmpty())
    	{
    		typeRendezvous.setIdTyperendezvous(generateMatricule());
    	}
        if (typeRendezvous.getNom() != null && !typeRendezvous.getIdTyperendezvous().isEmpty()) {
            return typeRendezvousRepository.save(typeRendezvous);
        }
        return null;
    }
   

    public List<TypeRendezvous> getAll() {
        return typeRendezvousRepository.findAll();
    }

    public TypeRendezvous delete(String idTyperendezvous) {
        Optional<TypeRendezvous> typeRendezvousOptional = typeRendezvousRepository.findById(idTyperendezvous);
        if (typeRendezvousOptional.isPresent()) {
            typeRendezvousRepository.deleteById(idTyperendezvous);
            return typeRendezvousOptional.get();
        }
        return null;
    }
    private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "TYP" + unique;
	}

}
