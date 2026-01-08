package com.example.gestionAnimaux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.gestionAnimaux.entites.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
	List<Message> findByVeterinaire_IdVeterinaireAndProprietaire_IdProprietaire(String idVeterinaire, String idProprietaire);

}
