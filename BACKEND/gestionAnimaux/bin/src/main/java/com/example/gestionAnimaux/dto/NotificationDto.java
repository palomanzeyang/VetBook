package com.example.gestionAnimaux.dto;

import java.time.LocalDateTime;

public class NotificationDto {
	
    private String id;

    private String type; 
    private String contenu;
    private boolean lu;
    private LocalDateTime timestamp;

    private String veterinaire;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getContenu() {
		return contenu;
	}


	public void setContenu(String contenu) {
		this.contenu = contenu;
	}


	public boolean isLu() {
		return lu;
	}


	public void setLu(boolean lu) {
		this.lu = lu;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public String getVeterinaire() {
		return veterinaire;
	}


	public void setVeterinaire(String veterinaire) {
		this.veterinaire = veterinaire;
	}
    
    
}
