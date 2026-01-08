package com.example.gestionAnimaux.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import com.example.gestionAnimaux.repository.VaccinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.dto.*;

import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.repository.AnimalRepository;

import com.example.gestionAnimaux.repository.EspeceRepository;
import com.example.gestionAnimaux.repository.ProprietaireRepository;
import com.example.gestionAnimaux.repository.*;


@Service
public class AnimauxService {

    @Autowired 
    private AnimalRepository animalRepository;
    @Autowired 
    private EspeceRepository especeRepository;
    @Autowired 
    private ProprietaireRepository proprietaireRepository;
    
    @Autowired private DossiermedicalRepository dossierRepo;
    @Autowired private ConsultationRepository consultationRepo;
    @Autowired private VaccinRepository vaccinRepo;
    @Autowired private TraitementPrescriptionRepository traitementRepo;
    @Autowired private MaladieRepository maladieRepo;
    @Autowired
    
    private VeterinaireRepository veterinaireRepository;
    
			    public AnimauxService(AnimalRepository animalRepository,EspeceRepository especeRepository,ProprietaireRepository proprietaireRepository)
			    { 
			    	this.animalRepository = animalRepository;
			    	this.especeRepository = especeRepository;
			    	this.proprietaireRepository = proprietaireRepository;
			    }
			    
			    private AnimalDto convertToDTO(Animal animal) {
			        AnimalDto dto = new AnimalDto();
			        dto.setIdAnimal(animal.getIdAnimal());
			        dto.setNomAnimal(animal.getNomAnimal());
			        dto.setAgeAnimal(animal.getAgeAnimal());
			        dto.setSexeAnimal(animal.getSexeAnimal());
			        dto.setCouleurAnimal(animal.getCouleurAnimal());
			        dto.setIdEspece(animal.getEspece().getIdEspece());
			        dto.setIdProprietaire(animal.getProprietaire().getIdProprietaire());
			        return dto;
			    }
			
			    public AnimalDto saveOrUpdate(AnimalDto animalDto) {
			        Proprietaire proprietaire = proprietaireRepository.findById(animalDto.getIdProprietaire())
			                .orElseThrow(() -> new RuntimeException("Propriétaire introuvable"));
			        
			        Veterinaire v = veterinaireRepository.findById(animalDto.getIdVeterinaire())
			                .orElseThrow(() -> new RuntimeException("Veterinaire introuvable"));
			        
			        Espece espece = especeRepository.findById(animalDto.getIdEspece())
			                .orElseThrow(() -> new RuntimeException("Espèce introuvable"));
			
			        Animal animal;
			        if (animalDto.getIdAnimal() != null) {
			            animal = animalRepository.findById(animalDto.getIdAnimal())
			                    .orElseThrow(() -> new RuntimeException("Animal introuvable"));
			            
			            animal.setNomAnimal(animalDto.getNomAnimal());
			            animal.setAgeAnimal(animalDto.getAgeAnimal());
			            animal.setCouleurAnimal(animalDto.getCouleurAnimal());
			            animal.setSexeAnimal(animalDto.getSexeAnimal());
			            animal.setEspece(espece);
			            animal.setProprietaire(proprietaire);
			            animal.setVeterinaire(v);
			        } else {
			            animal = new Animal();
			            animal.setIdAnimal(generateMatricule());  
			            animal.setNomAnimal(animalDto.getNomAnimal());
			            animal.setAgeAnimal(animalDto.getAgeAnimal());
			            animal.setCouleurAnimal(animalDto.getCouleurAnimal());
			            animal.setSexeAnimal(animalDto.getSexeAnimal());
			            animal.setEspece(espece);
			            animal.setProprietaire(proprietaire);
			            animal.setVeterinaire(v);
			
			        }
			
			        Animal savedAnimal = animalRepository.save(animal);
			
			        return convertToDTO(savedAnimal);
			    }
			
			    
			    public List<AnimalDto> getAll() {
			        List<Animal> vaccins = animalRepository.findAll();
			        return vaccins.stream()
			                      .map(this::convertToDTO)
			                      .collect(Collectors.toList());
			    }
			
			
			    public Animal delete(String idAnimal) {
			        Optional<Animal> animalOptional = animalRepository.findById(idAnimal);
			        if (animalOptional.isPresent()) {
			            animalRepository.deleteById(idAnimal);
			            return animalOptional.get();
			        }
			        return null;
			    }
			
				private String generateMatricule()
				{
					String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
					return "AN" + unique;
				}
				

	    
			    public List<AnimalDto> findByProprietaireId(String idProprietaire) {
			        List<Animal> animaux = animalRepository.findByProprietaire_IdProprietaire(idProprietaire);
			        return animaux.stream().map(this::convertToDTO).collect(Collectors.toList());
			    }
		
			    public List<AnimalDto> findByVetId(String idVeterinaire) {
			    	List<Animal> r = animalRepository.findByVeterinaire_IdVeterinaire(idVeterinaire);
			    	return r.stream().map(this::convertToDTO).collect(Collectors.toList());
			    }
			    
			    public long getNombreTotalAnimaux() {
			        return animalRepository.countAllAnimals();
			    }
			    public List<Consultation> getConsultationsByAnimal(String idAnimal) {
			        return dossierRepo.findByAnimal_IdAnimal(idAnimal).stream()
			            .flatMap(dossier -> consultationRepo.findByDossiermedical_IdDossiermedical(dossier.getIdDossiermedical()).stream())
			            .toList();
			    }

			    public List<Vaccin> getRappelsVaccin(String idAnimal, LocalDate date) {
			        // Conversion explicite si nécessaire
			        return vaccinRepo.findByDateRappelAfterAndDossiermedical_Animal_IdAnimal(
			            date.atStartOfDay(), // Convertit LocalDate en LocalDateTime
			            idAnimal
			        );
			    }
			    
			    
			    

			    public List<Maladie> getMaladiesByAnimal(String idAnimal) {
			        return maladieRepo.findByDossiermedical_Animal_IdAnimal(idAnimal);
			    }

			    public List<TraitementPrescription> getTraitementsByConsultation(String idConsultation) {
			        return traitementRepo.findByConsultation_IdConsultation(idConsultation);
			    }    
			   
			    public AnimalDetailsDto getAnimalDetails(String idAnimal) {
			        Animal animal = animalRepository.findById(idAnimal)
			            .orElseThrow(() -> new RuntimeException("Animal non trouvé"));

			        Dossiermedical dossier = dossierRepo.findByAnimal_IdAnimal(idAnimal)
			            .stream().findFirst().orElse(null); // Si 1 seul dossier par animal

			        if (dossier == null) {
			            return new AnimalDetailsDto(animal.getIdAnimal(), animal.getNomAnimal(), animal.getAgeAnimal(), animal.getSexeAnimal(), animal.getCouleurAnimal(), null);
			        }

			        List<ConsultationDTOs> consultations = consultationRepo.findByDossiermedical_IdDossiermedical(dossier.getIdDossiermedical())
			            .stream()
			            .map(c -> new ConsultationDTOs(
			                c.getIdConsultation(),
			                c.getSyntome(),
			                c.getDiagnostic(),
			                c.getDateConsultation(),
			                traitementRepo.findByConsultation_IdConsultation(c.getIdConsultation())
			                    .stream()
			                    .map(t -> new TraitementDTOs(
			                        t.getIdTraitement(),
			                        t.getContenuTraitement(),
			                        t.getDateDebut(),
			                        t.getDateFin(),
			                        t.getFrequence()))
			                    .toList()
			            ))
			            .toList();

			        List<MaladieDTOs> maladies = maladieRepo.findByDossiermedical_Animal_IdAnimal(idAnimal)
			            .stream()
			            .map(m -> new MaladieDTOs(
			                m.getIdMaladie(),
			                m.getNomMaladie(),
			                m.getDateDiagnostic(),
			                m.getCommentaire()))
			            .toList();

			        List<VaccinDTOs> vaccins = vaccinRepo.findByDossiermedical_Animal_IdAnimal(idAnimal).stream()
			            .map(v -> new VaccinDTOs(v.getIdVaccin(), v.getTypeVaccin(), v.getDateVaccin(), v.getDateRappel(), v.getStatut()))
			            .toList();
			        
			        
			     

			        DossierDTO dossierDTO = new DossierDTO();
			        dossierDTO.setIdDossier(dossier.getIdDossiermedical());
			        dossierDTO.setDateCreation(dossier.getDatecreation());
			        dossierDTO.setConsultations(consultations);
			        dossierDTO.setMaladies(maladies);
			        dossierDTO.setVaccins(vaccins);

			        return new AnimalDetailsDto(animal.getIdAnimal(), animal.getNomAnimal(), animal.getAgeAnimal(), animal.getSexeAnimal(), animal.getCouleurAnimal(), dossierDTO);
			    }

			    
}
