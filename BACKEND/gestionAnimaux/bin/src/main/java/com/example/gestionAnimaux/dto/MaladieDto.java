package com.example.gestionAnimaux.dto;

import java.util.Date;

import com.example.gestionAnimaux.entites.Dossiermedical;

public class MaladieDto {
	 
	    private String idMaladie;
	   
	    private String nomMaladie;
	    
	    private Date dateDiagnostic;
	    
	    private String commentaire;
	    
	    private String idDossiermedical;

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

		public String getIdDossiermedical() {
			return idDossiermedical;
		}

		public void setIdDossiermedical(String idDossiermedical) {
			this.idDossiermedical = idDossiermedical;
		}

		

		
	     
}
