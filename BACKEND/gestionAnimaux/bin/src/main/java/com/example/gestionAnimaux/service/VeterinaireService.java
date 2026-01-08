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
public class VeterinaireService {

    @Autowired
    private VeterinaireRepository veterinaireRepository;

    public VeterinaireService(VeterinaireRepository veterinaireRepository) {
        this.veterinaireRepository = veterinaireRepository;
    }
    private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "VET" + unique;
	}

    // Inscription (avec chiffrement du mot de passe)
//    public Veterinaire register(Veterinaire veterinaire) {
//        // Hacher le mot de passe avant de l'enregistrer
//        String hashedPassword = BCrypt.hashpw(veterinaire.getMdpVeterinaire(), BCrypt.gensalt());
//        veterinaire.setMdpVeterinaire(hashedPassword);
//	    veterinaire.setIdVeterinaire(generateMatricule());
//        return veterinaireRepository.save(veterinaire);
//    }

//    // Connexion (vérification du mot de passe)
//    public Optional<Veterinaire> login(String emailVeterinaire, String mdpVeterinaire) {
//        Optional<Veterinaire> veterinaire = veterinaireRepository.findByEmailVeterinaire(emailVeterinaire);
//        if (veterinaire.isPresent()) {
//            // Vérifier le mot de passe
//            if (BCrypt.checkpw(mdpVeterinaire, veterinaire.get().getMdpVeterinaire())) {
//                return veterinaire;
//            }
//        }
//        return Optional.empty();
//    }

    public List<Veterinaire> getAll() {
        return veterinaireRepository.findAll();
    }


    public Veterinaire delete(String idVeterinaire) {
        Optional<Veterinaire> vOptional = veterinaireRepository.findById(idVeterinaire);
        if (vOptional.isPresent()) {
            veterinaireRepository.deleteById(idVeterinaire);
            return vOptional.get();
        }
        return null;
    }

}
