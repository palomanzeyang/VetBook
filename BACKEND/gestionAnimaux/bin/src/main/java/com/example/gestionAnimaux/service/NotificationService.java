package com.example.gestionAnimaux.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.example.gestionAnimaux.dto.NotificationDto;
import com.example.gestionAnimaux.entites.Notification;
import com.example.gestionAnimaux.repository.NotificationRepository;

@Service
public class NotificationService {
	

    @Autowired
    private NotificationRepository notificationRepository;


    public NotificationDto create(Notification notification) {
        Notification savedNotification = notificationRepository.save(notification);
        return toDTO(savedNotification);
    }

    public List<NotificationDto> findByVetId(String idVeterinaire) {
        List<Notification> notifications = notificationRepository.findByVeterinaire_IdVeterinaireOrderByTimestampDesc(idVeterinaire);
        return notifications.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public NotificationDto update(String id, Notification notification) {
        return notificationRepository.findById(id).map(existingNotification -> {
            existingNotification.setType(notification.getType());
            existingNotification.setContenu(notification.getContenu());
            existingNotification.setLu(notification.isLu());
            existingNotification.setTimestamp(notification.getTimestamp());
            Notification updatedNotification = notificationRepository.save(existingNotification);
            return toDTO(updatedNotification);
        }).orElseThrow(() -> new RuntimeException("Notification non trouvée"));
    }

    public void delete(String id) {
        notificationRepository.deleteById(id);
    }

    private NotificationDto toDTO(Notification notification) {
        NotificationDto dto = new NotificationDto();
        dto.setId(notification.getId());
        dto.setType(notification.getType());
        dto.setContenu(notification.getContenu());
        dto.setLu(notification.isLu());
        dto.setTimestamp(notification.getTimestamp());
        // Convertir l'objet vétérinaire en chaîne (ID ou autre)
        dto.setVeterinaire(notification.getVeterinaire() != null ? notification.getVeterinaire().getIdVeterinaire() : null);
        return dto;
    }
}
