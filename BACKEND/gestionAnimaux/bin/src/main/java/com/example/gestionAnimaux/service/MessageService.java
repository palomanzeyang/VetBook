package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.dto.MessageDto;
import com.example.gestionAnimaux.entites.*;
import com.example.gestionAnimaux.repository.*;


@Service

public class MessageService {
	@Autowired
    private  MessageRepository messageRepository;
	@Autowired
    private  VeterinaireRepository veterinaireRepository;
	@Autowired
    private  ProprietaireRepository proprietaireRepository;
	
    private final SimpMessagingTemplate messagingTemplate;
	
    @Autowired
    public MessageService(@Qualifier("simpMessagingTemplate") SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    
    public MessageDto envoyerMessage(MessageDto dto) {
        Veterinaire vet = veterinaireRepository.findById(dto.getIdVeterinaire())
                .orElseThrow(() -> new RuntimeException("Vétérinaire non trouvé"));

        Proprietaire prop = proprietaireRepository.findById(dto.getIdProprietaire())
                .orElseThrow(() -> new RuntimeException("Propriétaire non trouvé"));

        Message message = new Message();
        message.setIdMessage(generateMatricule());
        message.setVeterinaire(vet);
        message.setProprietaire(prop);
        message.setContenu(dto.getContenu());
        message.setDateEnvoi(dto.getDateEnvoi());

        Message saved = messageRepository.save(message);

        MessageDto messageDTO = mapToDto(saved);

        // Envoi temps réel via WebSocket
        messagingTemplate.convertAndSendToUser(
                dto.getIdVeterinaire(), "/queue/messages", messageDTO
        );
        messagingTemplate.convertAndSendToUser(
                dto.getIdProprietaire(), "/queue/messages", messageDTO
        );

        return messageDTO;
    }

    public List<MessageDto> getMessagesBetween(String idVeterinaire, String idProprietaire) {
        return messageRepository.findByVeterinaire_IdVeterinaireAndProprietaire_IdProprietaire(
                idVeterinaire, idProprietaire
        ).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private MessageDto mapToDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setIdMessage(message.getIdMessage());
        dto.setContenu(message.getContenu());
        dto.setDateEnvoi(message.getDateEnvoi());
        dto.setIdVeterinaire(message.getVeterinaire().getIdVeterinaire());
        dto.setIdProprietaire(message.getProprietaire().getIdProprietaire());
        return dto;
    }
    private String generateMatricule()
	{
		String unique = UUID.randomUUID().toString().substring(0,6).toUpperCase();
		return "MES" + unique;
	}
}