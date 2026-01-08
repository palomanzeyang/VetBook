package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.dto.AnimalDto;
import com.example.gestionAnimaux.dto.TraitementDto;
import com.example.gestionAnimaux.entites.Animal;
import com.example.gestionAnimaux.entites.Consultation;
import com.example.gestionAnimaux.entites.Espece;
import com.example.gestionAnimaux.entites.Proprietaire;
import com.example.gestionAnimaux.entites.TraitementPrescription;
import com.example.gestionAnimaux.entites.Veterinaire;
import com.example.gestionAnimaux.repository.ConsultationRepository;
import com.example.gestionAnimaux.repository.TraitementPrescriptionRepository;

@Service
public class TraitementService {
	
	@Autowired
	TraitementPrescriptionRepository traitementRepository;
	
	@Autowired
	ConsultationRepository consultationRepository;
	
	 private TraitementDto convertToDTO(TraitementPrescription tp) {
		 TraitementDto dto = new TraitementDto();
	     dto.setIdTraitement(tp.getIdTraitement());
	     dto.setFrequence(tp.getFrequence());
	     dto.setDateFin(tp.getDateFin());
	     dto.setDateDebut(tp.getDateDebut());
	     dto.setContenuTraitement(tp.getContenuTraitement());
	     dto.setIdConsultation(tp.getConsultation().getIdConsultation());
	        return dto;
	    }
	 
	 public List<TraitementDto> getAll() {
	        List<TraitementPrescription> t = traitementRepository.findAll();
	        return t.stream()
	                      .map(this::convertToDTO)
	                      .collect(Collectors.toList());
	    }


	    public TraitementPrescription delete(String idTraitement) {
	        Optional<TraitementPrescription> tOptional = traitementRepository.findById(idTraitement);
	        if (tOptional.isPresent()) {
	            traitementRepository.deleteById(idTraitement);
	            return tOptional.get();
	        }
	        return null;
	    }

		private String generateMatricule()
		{
			String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
			return "TRT" + unique;
		}
		
		 public TraitementDto saveOrUpdate(TraitementDto traitementDto) 
		 {
			    
			Consultation cons = consultationRepository.findById(traitementDto.getIdTraitement())
					                .orElseThrow(() -> new RuntimeException("Traitement introuvable"));
				        
				 TraitementPrescription traitementPrescription;
				if(traitementDto.getIdTraitement() != null)
				{
				        	traitementPrescription = traitementRepository.findById(traitementDto.getIdTraitement())
				        	.orElseThrow(() -> new RuntimeException("Traitement introuvable"));
				        	
				        traitementPrescription.setFrequence(traitementDto.getFrequence());
				        traitementPrescription.setContenuTraitement(traitementDto.getContenuTraitement());
				        traitementPrescription.setDateDebut(traitementDto.getDateDebut());
				        traitementPrescription.setDateFin(traitementDto.getDateFin());
				        traitementPrescription.setConsultation(cons);
				 } 
		 
				 	else 
				 
				 {
				 	traitementPrescription = new TraitementPrescription();
		            traitementPrescription.setIdTraitement(generateMatricule());
		 			traitementPrescription.setFrequence(traitementDto.getFrequence());
			        traitementPrescription.setContenuTraitement(traitementDto.getContenuTraitement());
			        traitementPrescription.setDateDebut(traitementDto.getDateDebut());
			        traitementPrescription.setDateFin(traitementDto.getDateFin());
			        traitementPrescription.setConsultation(cons);

		     }

		        TraitementPrescription save = traitementRepository.save(traitementPrescription);

		        return convertToDTO(save);
		    }


}
