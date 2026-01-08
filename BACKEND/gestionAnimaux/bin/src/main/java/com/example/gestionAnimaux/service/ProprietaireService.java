package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.repository.*;
@Service
public class ProprietaireService {

    @Autowired
    private ProprietaireRepository proprietaireRepository;

    public ProprietaireService(ProprietaireRepository proprietaireRepository) {
        this.proprietaireRepository = proprietaireRepository;
    }
    private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "PRO" + unique;
	}

    public Proprietaire register(Proprietaire proprietaire) {
        String hashPassword = BCrypt.hashpw(proprietaire.getMdpProprietaire(), BCrypt.gensalt());
        proprietaire.setMdpProprietaire(hashPassword);
	    proprietaire.setIdProprietaire(generateMatricule());
        return proprietaireRepository.save(proprietaire);
    }

    public Optional<Proprietaire> login(String emailProprietaire, String mdpProprietaire) {
        Optional<Proprietaire> proprietaire = proprietaireRepository.findByEmailProprietaire(emailProprietaire);
        if (proprietaire.isPresent()) {
            if (BCrypt.checkpw(mdpProprietaire, proprietaire.get().getMdpProprietaire())) {
                return proprietaire;
            }
        }
        return Optional.empty();
    }

    public List<Proprietaire> getAll() {
        return proprietaireRepository.findAll();
    }


    public Proprietaire delete(String idProprietaire) {
        Optional<Proprietaire> proprietaireOptional = proprietaireRepository.findById(idProprietaire);
        if (proprietaireOptional.isPresent()) {
            proprietaireRepository.deleteById(idProprietaire);
            return proprietaireOptional.get();
        }
        return null;
    }

    
}
