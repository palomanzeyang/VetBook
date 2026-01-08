import 'package:monveto/models/Consultation.dart';
import 'package:monveto/models/Maladie.dart';
import 'package:monveto/models/Vaccin.dart';

class Dossiermedical {
  final String idDossier;
  final String idAnimal;
  final List<Vaccin> vaccins;
  final List<Consultation> consultations;
  final List<Maladie> maladies;
  final DateTime dateCreation;

  Dossiermedical({
    required this.idDossier,
    required this.idAnimal,
    required this.vaccins,
    required this.consultations,
    required this.maladies,
    required this.dateCreation,
  });

  factory Dossiermedical.fromJson(Map<String, dynamic> json) {
    return Dossiermedical(
      idDossier: json['idDossier'] ?? '',
      idAnimal: json['idAnimal'] ?? '',
      vaccins: json['vaccins'] != null
          ? List<Vaccin>.from(json['vaccins'].map((x) => Vaccin.fromJson(x)))
          : [],
      consultations: json['consultations'] != null
          ? List<Consultation>.from(json['consultations'].map((x) => Consultation.fromJson(x)))
          : [],
      maladies: json['maladies'] != null
          ? List<Maladie>.from(json['maladies'].map((x) => Maladie.fromJson(x)))
          : [],
      dateCreation: json['dateCreation'] != null 
          ? DateTime.parse(json['dateCreation'])
          : DateTime.now(),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'idDossier': idDossier,
      'idAnimal': idAnimal,
      'vaccins': vaccins.map((x) => x.toJson()).toList(),
      'consultations': consultations.map((x) => x.toJson()).toList(),
      'maladies': maladies.map((x) => x.toJson()).toList(),
      'dateCreation': dateCreation.toIso8601String(),
    };
  }
}