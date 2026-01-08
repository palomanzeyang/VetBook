package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.dto.RendezvousDto;
import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.repository.*;

@Service
public class RendezvousService {
	@Autowired
    private AnimalRepository animalRepository;
    @Autowired 
    private RendezvousRepository rendezvousRepository;
    @Autowired 
    private VeterinaireRepository veterinaireRepository;
    @Autowired
    private TypeRendezvousRepository typeRendezvousRepository;
    
    
    
    private RendezvousDto convertToDTO(Rendezvous rdv) {
        RendezvousDto dto = new RendezvousDto();
        dto.setIdRendezvous(rdv.getIdRendezvous());
        dto.setDateRendezvous(rdv.getDateRendezvous());
        dto.setObjet(rdv.getObjet());
        dto.setStatut(rdv.getStatut());
        dto.setIdAnimal(rdv.getAnimal().getIdAnimal());
        dto.setIdTyperendezvous(rdv.getTyperendezvous().getIdTyperendezvous());
        dto.setIdVeterinaire(rdv.getVeterinaire().getIdVeterinaire());
        return dto;
    }
    public RendezvousDto saveOrUpdate(RendezvousDto rdvDto) {
        Animal animal = animalRepository.findById(rdvDto.getIdAnimal())
                .orElseThrow(() -> new RuntimeException("Animal introuvable"));

        TypeRendezvous trdv = typeRendezvousRepository.findById(rdvDto.getIdTyperendezvous())
                .orElseThrow(() -> new RuntimeException("Type de rendez-vous introuvable"));

        Veterinaire veterinaire = veterinaireRepository.findById(rdvDto.getIdVeterinaire())
                .orElseThrow(() -> new RuntimeException("Vétérinaire introuvable"));

        Rendezvous rendezvous;
        if (rdvDto.getIdRendezvous() != null) {
            rendezvous = rendezvousRepository.findById(rdvDto.getIdRendezvous())
                    .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable"));

            rendezvous.setDateRendezvous(rdvDto.getDateRendezvous());
            rendezvous.setObjet(rdvDto.getObjet());
            rendezvous.setStatut(rdvDto.getStatut());
            rendezvous.setTyperendezvous(trdv);
            rendezvous.setVeterinaire(veterinaire);
        } else {
            rendezvous = new Rendezvous();
            rendezvous.setIdRendezvous(generateMatricule()); 
            rendezvous.setDateRendezvous(rdvDto.getDateRendezvous());
            rendezvous.setObjet(rdvDto.getObjet());
            rendezvous.setStatut(rdvDto.getStatut());
            rendezvous.setAnimal(animal);
            rendezvous.setTyperendezvous(trdv);
            rendezvous.setVeterinaire(veterinaire);
        }

        Rendezvous savedRendezvous = rendezvousRepository.save(rendezvous);

        return convertToDTO(savedRendezvous);
    }

    
                  
    public List<RendezvousDto> getAll() {
        List<Rendezvous> vaccins = rendezvousRepository.findAll();
        return vaccins.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }

    public Rendezvous delete(String idRendezvous) {
        Optional<Rendezvous> rdvOptional = rendezvousRepository.findById(idRendezvous);
        if (rdvOptional.isPresent()) {
            rendezvousRepository.deleteById(idRendezvous);
            return rdvOptional.get();
        }
        return null;
    }
    
    public List<RendezvousDto> findByVeterinaireId(String idVeterinaire) {
        return rendezvousRepository.findByVeterinaire_IdVeterinaire(idVeterinaire).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<RendezvousDto> findByProprietaireId(String idAnimal) {
        return rendezvousRepository.findByAnimal_IdAnimal(idAnimal).stream()
        		.map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
	private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "RDV" + unique;
	}
	
	public long getNombreTotalRendezvous() {
        return rendezvousRepository.countAllRendezvous();
    }
	
	public long getNombreRendezvousByVeterinaire(String idVeterinaire) {
        return rendezvousRepository.countByVeterinaire(idVeterinaire);
    }
}
