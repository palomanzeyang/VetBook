package com.example.gestionAnimaux.dto;

import java.util.Date;



public class RendezvousDto {
	
	private String idRendezvous;
    private Date dateRendezvous;
    private String objet;
    private String statut;
    private String idAnimal;
    private String idTyperendezvous;
    private String idVeterinaire;
	public String getIdRendezvous() {
		return idRendezvous;
	}
	public void setIdRendezvous(String idRendezvous) {
		this.idRendezvous = idRendezvous;
	}
	
	public Date getDateRendezvous() {
		return dateRendezvous;
	}
	public void setDateRendezvous(Date dateRendezvous) {
		this.dateRendezvous = dateRendezvous;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getIdAnimal() {
		return idAnimal;
	}
	public void setIdAnimal(String idAnimal) {
		this.idAnimal = idAnimal;
	}
	public String getIdTyperendezvous() {
		return idTyperendezvous;
	}
	public void setIdTyperendezvous(String idTyperendezvous) {
		this.idTyperendezvous = idTyperendezvous;
	}
	public String getIdVeterinaire() {
		return idVeterinaire;
	}
	public void setIdVeterinaire(String idVeterinaire) {
		this.idVeterinaire = idVeterinaire;
	}
    
    
}
