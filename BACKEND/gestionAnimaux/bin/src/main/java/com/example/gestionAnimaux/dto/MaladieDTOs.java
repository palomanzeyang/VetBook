package com.example.gestionAnimaux.dto;

import java.util.Date;

public class MaladieDTOs {
	 private String idMaladie;
	    private String nomMaladie;
	    private Date dateDiagnostic;
	    private String commentaire;

	    public MaladieDTOs(String id, String nom, Date date, String commentaire) {
	        this.idMaladie = id;
	        this.nomMaladie = nom;
	        this.dateDiagnostic = date;
	        this.commentaire = commentaire;
	    }

		public String getIdMaladie() {
			return idMaladie;
		}

		public void setIdMaladie(String idMaladie) {
			this.idMaladie = idMaladie;
		}

		public String getNomMaladie() {
			return nomMaladie;
		}

		public void setNomMaladie(String nomMaladie) {
			this.nomMaladie = nomMaladie;
		}

		public Date getDateDiagnostic() {
			return dateDiagnostic;
		}

		public void setDateDiagnostic(Date dateDiagnostic) {
			this.dateDiagnostic = dateDiagnostic;
		}

		public String getCommentaire() {
			return commentaire;
		}

		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}
	    
	    
}
