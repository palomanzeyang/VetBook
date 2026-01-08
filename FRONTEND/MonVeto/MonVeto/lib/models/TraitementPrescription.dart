class TraitementPrescription {
  final String idTraitement;
  final String contenuTraitement;
  final DateTime dateDebut;
  final DateTime dateFin;
  final String frequence;
  final String idConsultation;

  TraitementPrescription({
    required this.idTraitement,
    required this.contenuTraitement,
    required this.dateDebut,
    required this.dateFin,
    required this.frequence,
    required this.idConsultation,
  });

  factory TraitementPrescription.fromJson(Map<String, dynamic> json) {
    return TraitementPrescription(
      idTraitement: json['idTraitement'],
      contenuTraitement: json['contenuTraitement'],
      dateDebut: DateTime.parse(json['dateDebut']),
      dateFin: DateTime.parse(json['dateFin']),
      frequence: json['frequence'],
      idConsultation: json['idConsultation']['idConsultation'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'idTraitement': idTraitement,
      'contenuTraitement': contenuTraitement,
      'dateDebut': dateDebut.toIso8601String(),
      'dateFin': dateFin.toIso8601String(),
      'frequence': frequence,
      'idConsultation': {
        'idConsultation': idConsultation,
      },
    };
  }
}
