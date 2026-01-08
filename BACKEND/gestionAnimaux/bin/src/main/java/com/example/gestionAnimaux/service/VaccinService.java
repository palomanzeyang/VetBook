package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.dto.ConsultationDto;
import com.example.gestionAnimaux.dto.VaccinDto;
import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.repository.*;
@Service
public class VaccinService {

    @Autowired
    private VaccinRepository vaccinRepository;
    @Autowired
    private DossiermedicalRepository dossiermedicalRepository;
    
    private VaccinDto convertToDTO(Vaccin v)
    {
    	VaccinDto dto = new VaccinDto();
    	dto.setIdVaccin(v.getIdVaccin());
    	dto.setDateRappel(v.getDateRappel());
    	dto.setDateVaccin(v.getDateVaccin());
    	dto.setStatut(v.getStatut());
    	dto.setTypeVaccin(v.getTypeVaccin());
    	dto.setIdDossiermedical(v.getDossiermedical().getIdDossiermedical());
    	
        return dto;
    }
    
    public VaccinDto save(VaccinDto vaccinDto)
    {
        Dossiermedical dm = dossiermedicalRepository.findById(vaccinDto.getIdDossiermedical())
                .orElseThrow(() -> new RuntimeException("Dossier médical avec l'ID " + vaccinDto.getIdDossiermedical() + " introuvable"));

        Vaccin c = new Vaccin();
        c.setIdVaccin(generateMatricule());
        c.setDateVaccin(vaccinDto.getDateVaccin());
        c.setDateRappel(vaccinDto.getDateRappel());
        c.setStatut(vaccinDto.getStatut());
        c.setTypeVaccin(vaccinDto.getTypeVaccin());
        c.setDossiermedical(dm);

        Vaccin saved = vaccinRepository.save(c);

        return convertToDTO(saved);
    }


    public List<VaccinDto> getAll() {
        List<Vaccin> vaccins = vaccinRepository.findAll();
        return vaccins.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }


    public Vaccin getById(String idVaccin) {
        Optional<Vaccin> vaccinOptional = vaccinRepository.findById(idVaccin);
        return vaccinOptional.orElse(null);
    }

    public Vaccin delete(String idVaccin) {
        Optional<Vaccin> vaccinOptional = vaccinRepository.findById(idVaccin);
        if (vaccinOptional.isPresent()) {
            vaccinRepository.deleteById(idVaccin);
            return vaccinOptional.get();
        }
        return null;
    }

    private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "VAC" + unique;
	}
}
