package com.example.gestionAnimaux.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class VaccinDTOs {
	 private String idVaccin;
	    private String typeVaccin;
	    private LocalDate  dateVaccin;
	    private LocalDateTime  dateRappel;
	    private String statut;

	    public VaccinDTOs(String id, String type, LocalDate  date, LocalDateTime  date2, String statut) {
	        this.idVaccin = id;
	        this.typeVaccin = type;
	        this.dateVaccin = date;
	        this.dateRappel = date2;
	        this.statut = statut;
	    }

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

		public LocalDate  getDateVaccin() {
			return dateVaccin;
		}

		public void setDateVaccin(LocalDate  dateVaccin) {
			this.dateVaccin = dateVaccin;
		}

		public LocalDateTime  getDateRappel() {
			return dateRappel;
		}

		public void setDateRappel(LocalDateTime  dateRappel) {
			this.dateRappel = dateRappel;
		}

		public String getStatut() {
			return statut;
		}

		public void setStatut(String statut) {
			this.statut = statut;
		}
	    
	    
}
