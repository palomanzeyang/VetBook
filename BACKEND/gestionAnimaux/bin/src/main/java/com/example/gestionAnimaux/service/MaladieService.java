package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gestionAnimaux.dto.MaladieDto;
import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.repository.*;
@Service
public class MaladieService {

    @Autowired
    private MaladieRepository maladieRepository;
    @Autowired
    private DossiermedicalRepository dossiermedicalRepository;

    private MaladieDto convertToDTO(Maladie maladie) {
    	MaladieDto dto = new MaladieDto();
    	dto.setIdMaladie(maladie.getIdMaladie());
    	dto.setNomMaladie(maladie.getNomMaladie());
    	dto.setDateDiagnostic(maladie.getDateDiagnostic());
    	dto.setCommentaire(maladie.getCommentaire());
    	dto.setIdDossiermedical(maladie.getDossiermedical().getIdDossiermedical());
   
        return dto;
    }
    
    public MaladieDto saveOrUpdate(MaladieDto maladieDto) {
        Dossiermedical dm = dossiermedicalRepository.findById(maladieDto.getIdDossiermedical())
                .orElseThrow(() -> new RuntimeException("Dossier médical introuvable"));

        Maladie maladie;
        if (maladieDto.getIdMaladie() != null) {
            maladie = maladieRepository.findById(maladieDto.getIdMaladie())
                    .orElseThrow(() -> new RuntimeException("Maladie introuvable"));

            maladie.setDateDiagnostic(maladieDto.getDateDiagnostic());
            maladie.setNomMaladie(maladieDto.getNomMaladie());
            maladie.setCommentaire(maladieDto.getCommentaire());
            maladie.setDossiermedical(dm);
        } else {
            maladie = new Maladie();
            maladie.setIdMaladie(generateMatricule());  // Génération d'un ID unique
            maladie.setDateDiagnostic(maladieDto.getDateDiagnostic());
            maladie.setNomMaladie(maladieDto.getNomMaladie());
            maladie.setCommentaire(maladieDto.getCommentaire());
            maladie.setDossiermedical(dm);
        }

        Maladie savedMaladie = maladieRepository.save(maladie);

        return convertToDTO(savedMaladie);
    }

        
    public List<MaladieDto> getAll() {
        List<Maladie> vaccins = maladieRepository.findAll();
        return vaccins.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }



    public Maladie delete(String idMaladie) {
        Optional<Maladie> maladieOptional = maladieRepository.findById(idMaladie);
        if (maladieOptional.isPresent()) {
            maladieRepository.deleteById(idMaladie);
            return maladieOptional.get();
        }
        return null;
    }

    private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "MAL" + unique;
	}
}
