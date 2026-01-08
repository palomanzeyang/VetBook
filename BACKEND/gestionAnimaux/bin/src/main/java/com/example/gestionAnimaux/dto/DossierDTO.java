package com.example.gestionAnimaux.dto;

import java.util.Date;
import java.util.List;

public class DossierDTO {

	 private String idDossier;
	    private Date dateCreation;
	    private List<ConsultationDTOs> consultations;
	    private List<MaladieDTOs> maladies;
	    private List<VaccinDTOs> vaccins;
		public String getIdDossier() {
			return idDossier;
		}
		public void setIdDossier(String idDossier) {
			this.idDossier = idDossier;
		}
		public Date getDateCreation() {
			return dateCreation;
		}
		public void setDateCreation(java.util.Date date) {
			this.dateCreation = date;
		}
		public List<ConsultationDTOs> getConsultations() {
			return consultations;
		}
		public void setConsultations(List<ConsultationDTOs> consultations) {
			this.consultations = consultations;
		}
		public List<MaladieDTOs> getMaladies() {
			return maladies;
		}
		public void setMaladies(List<MaladieDTOs> maladies) {
			this.maladies = maladies;
		}
		public List<VaccinDTOs> getVaccins() {
			return vaccins;
		}
		public void setVaccins(List<VaccinDTOs> vaccins) {
			this.vaccins = vaccins;
		}
	    
	    
}
