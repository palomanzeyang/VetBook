package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.dto.AnimalDto;
import com.example.gestionAnimaux.dto.DossierMedicalDto;
import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.repository.*;
@Service
public class DossierMedicalService {

    @Autowired
    private DossiermedicalRepository dossiermedicalRepository;
    @Autowired
    private AnimalRepository animalRepository;

    private DossierMedicalDto convertToDTO(Dossiermedical dm) {
    	DossierMedicalDto dto = new DossierMedicalDto();
    	dto.setIdDossiermedical(dm.getIdDossiermedical());
    	dto.setDatecreation(dm.getDatecreation());
    	dto.setIdAnimal(dm.getAnimal().getIdAnimal());
        return dto;
    }
    
    public DossierMedicalDto saveOrUpdate(DossierMedicalDto dmdto) {
        // Vérification de l'existence de l'animal
        Animal animal = animalRepository.findById(dmdto.getIdAnimal())
                .orElseThrow(() -> new RuntimeException("Animal introuvable"));

        Dossiermedical dossiermedical;
        if (dmdto.getIdDossiermedical() != null) {
            dossiermedical = dossiermedicalRepository.findById(dmdto.getIdDossiermedical())
                    .orElseThrow(() -> new RuntimeException("Dossier médical introuvable"));

            dossiermedical.setDatecreation(dmdto.getDatecreation());
            dossiermedical.setAnimal(animal);
        } else {
            dossiermedical = new Dossiermedical();
            dossiermedical.setIdDossiermedical(generateMatricule());  // Génération d'un ID unique
            dossiermedical.setDatecreation(dmdto.getDatecreation());
            dossiermedical.setAnimal(animal);
        }

        Dossiermedical savedDossierMedical = dossiermedicalRepository.save(dossiermedical);

        return convertToDTO(savedDossierMedical);
    }


    public List<DossierMedicalDto> getAll() {
        List<Dossiermedical> vaccins = dossiermedicalRepository.findAll();
        return vaccins.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }


    public Dossiermedical delete(String idDossiermedical) {
        Optional<Dossiermedical> dmOptional = dossiermedicalRepository.findById(idDossiermedical);
        if (dmOptional.isPresent()) {
            dossiermedicalRepository.deleteById(idDossiermedical);
            return dmOptional.get();
        }
        return null;
    }

	private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "DM" + unique;
	}

	
	 public DossierMedicalDto update(String idDossierrmedical, Dossiermedical dossiermedical) {
		 Animal animal = animalRepository.findById(dossiermedical.getAnimal().getIdAnimal()).orElseThrow(()-> new RuntimeException("id introuvable"));

	        return dossiermedicalRepository.findById(idDossierrmedical).map(d -> {
	        	d.setDatecreation(dossiermedical.getDatecreation());
	        	d.setAnimal(animal);
	            return convertToDTO(dossiermedicalRepository.save(d));
	        }).orElseThrow(() -> new RuntimeException("Animal non trouvé"));
	    }

    
}
