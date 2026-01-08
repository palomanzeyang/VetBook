package com.example.gestionAnimaux.dto;

import java.util.Date;

public class MessageDto {
	private String idMessage;
   
    private String contenu;
    
    private Date dateEnvoi;
   
    private String idVeterinaire;
    
    private String idProprietaire;

	public String getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public String getIdVeterinaire() {
		return idVeterinaire;
	}

	public void setIdVeterinaire(String idVeterinaire) {
		this.idVeterinaire = idVeterinaire;
	}

	public String getIdProprietaire() {
		return idProprietaire;
	}

	public void setIdProprietaire(String idProprietaire) {
		this.idProprietaire = idProprietaire;
	}
    
    
    
}
