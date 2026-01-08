import 'package:monveto/models/Dossiermedical%20.dart';

class Vaccin {
  final String idVaccin;
  final String typeVaccin;
  final DateTime dateVaccin;
  final DateTime dateRappel;
  final String statut;
  final Dossiermedical dossiermedical;

  Vaccin({
    required this.idVaccin,
    required this.typeVaccin,
    required this.dateVaccin,
    required this.dateRappel,
    required this.statut,
    required this.dossiermedical,
  });

  factory Vaccin.fromJson(Map<String, dynamic> json) {
    return Vaccin(
      idVaccin: json['idVaccin'] as String,
      typeVaccin: json['typeVaccin'] as String,
      dateVaccin: DateTime.parse(json['dateVaccin'] as String),
      dateRappel: DateTime.parse(json['dateRappel'] as String),
      statut: json['statut'] as String,
      dossiermedical: Dossiermedical.fromJson(json['dossiermedical'] as Map<String, dynamic>),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'idVaccin': idVaccin,
      'typeVaccin': typeVaccin,
      'dateVaccin': dateVaccin.toIso8601String(),
      'dateRappel': dateRappel.toIso8601String(),
      'statut': statut,
      'dossiermedical': dossiermedical.toJson(),
    };
  }

  Vaccin copyWith({
    String? idVaccin,
    String? typeVaccin,
    DateTime? dateVaccin,
    DateTime? dateRappel,
    String? statut,
    Dossiermedical? dossiermedical,
  }) {
    return Vaccin(
      idVaccin: idVaccin ?? this.idVaccin,
      typeVaccin: typeVaccin ?? this.typeVaccin,
      dateVaccin: dateVaccin ?? this.dateVaccin,
      dateRappel: dateRappel ?? this.dateRappel,
      statut: statut ?? this.statut,
      dossiermedical: dossiermedical ?? this.dossiermedical,
    );
  }

  @override
  String toString() {
    return 'Vaccin($idVaccin - $typeVaccin - ${dateVaccin.toLocal()})';
  }

  // Méthode utilitaire pour vérifier si un rappel est nécessaire
  bool isRappelNeeded() {
    return DateTime.now().isAfter(dateRappel);
  }
}