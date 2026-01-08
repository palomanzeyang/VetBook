package com.example.gestionAnimaux.dto;

import java.util.Date;
import java.util.List;

public class ConsultationDTOs {
	private String idConsultation;
    private String syntome;
    private String diagnostic;
    private Date dateConsultation;
    private List<TraitementDTOs> traitements;

    public ConsultationDTOs(String id, String syntome, String diagnostic, Date date, List<TraitementDTOs> traitements) {
        this.idConsultation = id;
        this.syntome = syntome;
        this.diagnostic = diagnostic;
        this.dateConsultation = date;
        this.traitements = traitements;
    }

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

	public List<TraitementDTOs> getTraitements() {
		return traitements;
	}

	public void setTraitements(List<TraitementDTOs> traitements) {
		this.traitements = traitements;
	}

	
    
    
}
