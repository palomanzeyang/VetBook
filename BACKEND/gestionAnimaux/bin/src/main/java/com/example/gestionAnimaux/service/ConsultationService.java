package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.dto.AnimalDto;
import com.example.gestionAnimaux.dto.ConsultationDto;
import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.repository.*;
@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private DossiermedicalRepository dossiermedicalRepository;
    

    
    
    private ConsultationDto convertToDTO(Consultation a)
    {
    	ConsultationDto dto = new ConsultationDto();
    	dto.setIdConsultation(a.getIdConsultation());
    	dto.setDateConsultation(a.getDateConsultation());
    	dto.setDiagnostic(a.getDiagnostic());
    	dto.setSyntome(a.getSyntome());
    	dto.setIdDossiermedical(a.getDossiermedical().getIdDossiermedical());
    	
        return dto;
    }
    
    public ConsultationDto saveOrUpdate(ConsultationDto consultationDto) {
        Dossiermedical dm = dossiermedicalRepository.findById(consultationDto.getIdDossiermedical())
                .orElseThrow(() -> new RuntimeException("Dossier médical introuvable"));
        
        Consultation consultation;
        if (consultationDto.getIdConsultation() != null) {
            consultation = consultationRepository.findById(consultationDto.getIdConsultation())
                    .orElseThrow(() -> new RuntimeException("Consultation introuvable"));
            
            consultation.setDateConsultation(consultationDto.getDateConsultation());
            consultation.setDiagnostic(consultationDto.getDiagnostic());
            consultation.setSyntome(consultationDto.getSyntome());
            consultation.setDossiermedical(dm);
        } else {
            consultation = new Consultation();
            consultation.setIdConsultation(generateMatricule());  
            consultation.setDateConsultation(consultationDto.getDateConsultation());
            consultation.setDiagnostic(consultationDto.getDiagnostic());
            consultation.setSyntome(consultationDto.getSyntome());
            consultation.setDossiermedical(dm);
        }
        Consultation savedConsultation = consultationRepository.save(consultation);

        return convertToDTO(savedConsultation);
    }  

    public List<ConsultationDto> getAll() {
        List<Consultation> vaccins = consultationRepository.findAll();
        return vaccins.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }


    public Consultation delete(String idConsultation) {
        Optional<Consultation> consultationOptional = consultationRepository.findById(idConsultation);
        if (consultationOptional.isPresent()) {
            consultationRepository.deleteById(idConsultation);
            return consultationOptional.get();
        }
        return null;
    }
    private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "CON" + unique;
	}
    
}
