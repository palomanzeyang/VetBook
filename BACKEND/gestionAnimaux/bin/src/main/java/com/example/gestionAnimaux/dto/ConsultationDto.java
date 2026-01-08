package com.example.gestionAnimaux.dto;

import java.util.Date;

public class ConsultationDto {
	
   
    private String idConsultation;
  
    private String syntome;
   
    private String diagnostic;
    
    private Date dateConsultation;
    
    private String idDossiermedical;

	public String getIdConsultation() {
		return idConsultation;
	}

	public void setIdConsultation(String idConsultation) {
		this.idConsultation = idConsultation;
	}

	public String getSyntome() {
		return syntome;
	}

	public void setSyntome(String syntome) {
		this.syntome = syntome;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public Date getDateConsultation() {
		return dateConsultation;
	}

	public void setDateConsultation(Date dateConsultation) {
		this.dateConsultation = dateConsultation;
	}

	public String getIdDossiermedical() {
		return idDossiermedical;
	}

	public void setIdDossiermedical(String idDossiermedical) {
		this.idDossiermedical = idDossiermedical;
	}

	
    
    
    
}
