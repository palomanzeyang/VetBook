package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.dto.AnimalDto;
import com.example.gestionAnimaux.dto.ConsultationDto;
import com.example.gestionAnimaux.dto.EspeceDto;
import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.repository.*;


@Service
public class EspeceService {

    @Autowired
    private EspeceRepository especeRepository;

    public Espece saveOrUpdate(Espece user) {
    	if(user.getIdEspece() == null || user.getIdEspece().isEmpty())
    	{
    		user.setIdEspece(generateMatricule());
    	}
        if (!user.getType().isEmpty())
        {
            return especeRepository.save(user);
        }
        return null;
    }
    private EspeceDto convertToDTO(Espece espece) {
        EspeceDto dto = new EspeceDto();
        dto.setIdEspece(espece.getIdEspece());
        dto.setType(espece.getType());
        
        return dto;
    }

    public List<EspeceDto> getAll() {
        List<Espece> esp = especeRepository.findAll();
        return esp.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }

    public Espece delete(String idespece) {
        Optional<Espece> especeOptional = especeRepository.findById(idespece);
        if (especeOptional.isPresent()) {
            especeRepository.deleteById(idespece);
            return especeOptional.get();
        }
        return null;
    }

    private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "ESP" + unique;
	}
    
    public long getNombreTotalEspeces() {
        return especeRepository.countAllEspeces();
    }
}
