package com.example.gestionAnimaux.dto;

public class AnimalDetailsDto {
	 	private String idAnimal;
	    private String nomAnimal;
	    private String ageAnimal;
	    private String sexeAnimal;
	    private String couleurAnimal;
	    private DossierDTO dossier;

	    public AnimalDetailsDto(String id, String nom, String age, String sexe, String couleur, DossierDTO dossier) {
	        this.idAnimal = id;
	        this.nomAnimal = nom;
	        this.ageAnimal = age;
	        this.sexeAnimal = sexe;
	        this.couleurAnimal = couleur;
	        this.dossier = dossier;
	    }

		public String getIdAnimal() {
			return idAnimal;
		}

		public void setIdAnimal(String idAnimal) {
			this.idAnimal = idAnimal;
		}

		public String getNomAnimal() {
			return nomAnimal;
		}

		public void setNomAnimal(String nomAnimal) {
			this.nomAnimal = nomAnimal;
		}

		public String getAgeAnimal() {
			return ageAnimal;
		}

		public void setAgeAnimal(String ageAnimal) {
			this.ageAnimal = ageAnimal;
		}

		public String getSexeAnimal() {
			return sexeAnimal;
		}

		public void setSexeAnimal(String sexeAnimal) {
			this.sexeAnimal = sexeAnimal;
		}

		public String getCouleurAnimal() {
			return couleurAnimal;
		}

		public void setCouleurAnimal(String couleurAnimal) {
			this.couleurAnimal = couleurAnimal;
		}

		public DossierDTO getDossier() {
			return dossier;
		}

		public void setDossier(DossierDTO dossier) {
			this.dossier = dossier;
		}
	    
	    
	}

