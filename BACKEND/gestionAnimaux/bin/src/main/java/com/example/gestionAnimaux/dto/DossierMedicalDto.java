package com.example.gestionAnimaux.dto;

import java.util.Date;

public class DossierMedicalDto {
	
	private String idDossiermedical;
	
	private Date datecreation;
	
	private String idAnimal;

	public String getIdDossiermedical() {
		return idDossiermedical;
	}

	public void setIdDossiermedical(String idDossiermedical) {
		this.idDossiermedical = idDossiermedical;
	}

	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public String getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(String idAnimal) {
		this.idAnimal = idAnimal;
	}
	
	
}
