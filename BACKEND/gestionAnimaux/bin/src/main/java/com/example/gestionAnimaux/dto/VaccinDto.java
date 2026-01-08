package com.example.gestionAnimaux.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VaccinDto {
	
    private String idVaccin;
    
    private String typeVaccin;
    

   
    private String idDossiermedical;
    
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateVaccin;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime dateRappel;
    
    private String statut;

	public String getIdVaccin() {
		return idVaccin;
	}

	public void setIdVaccin(String idVaccin) {
		this.idVaccin = idVaccin;
	}

	public String getTypeVaccin() {
		return typeVaccin;
	}

	public void setTypeVaccin(String typeVaccin) {
		this.typeVaccin = typeVaccin;
	}

	public LocalDate getDateVaccin() {
		return dateVaccin;
	}

	public void setDateVaccin(LocalDate dateVaccin) {
		this.dateVaccin = dateVaccin;
	}

	public LocalDateTime getDateRappel() {
		return dateRappel;
	}

	public void setDateRappel(LocalDateTime dateRappel) {
		this.dateRappel = dateRappel;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getIdDossiermedical() {
		return idDossiermedical;
	}

	public void setIdDossiermedical(String idDossiermedical) {
		this.idDossiermedical = idDossiermedical;
	}
    
    
    
}
