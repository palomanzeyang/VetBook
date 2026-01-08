package com.example.gestionAnimaux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestionAnimaux.entites.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String>{
    List<Notification> findByVeterinaire_IdVeterinaireOrderByTimestampDesc(String idVeterinaire);
}
