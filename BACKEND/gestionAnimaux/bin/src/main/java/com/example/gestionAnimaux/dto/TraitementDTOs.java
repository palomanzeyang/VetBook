package com.example.gestionAnimaux.dto;

import java.util.Date;

public class TraitementDTOs {
	    private String idTraitement;
	    private String contenuTraitement;
	    private Date dateDebut;
	    private Date dateFin;
	    private String frequence;

	    public TraitementDTOs(String id, String contenu,Date date, Date date2, String frequence) {
	        this.idTraitement = id;
	        this.contenuTraitement = contenu;
	        this.dateDebut = date;
	        this.dateFin = date2;
	        this.frequence = frequence;
	    }

		public String getIdTraitement() {
			return idTraitement;
		}

		public void setIdTraitement(String idTraitement) {
			this.idTraitement = idTraitement;
		}

		public String getContenuTraitement() {
			return contenuTraitement;
		}

		public void setContenuTraitement(String contenuTraitement) {
			this.contenuTraitement = contenuTraitement;
		}

		public Date getDateDebut() {
			return dateDebut;
		}

		public void setDateDebut(Date dateDebut) {
			this.dateDebut = dateDebut;
		}

		public Date getDateFin() {
			return dateFin;
		}

		public void setDateFin(Date dateFin) {
			this.dateFin = dateFin;
		}

		public String getFrequence() {
			return frequence;
		}

		public void setFrequence(String frequence) {
			this.frequence = frequence;
		}
	    
	    
}
