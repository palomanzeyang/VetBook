import 'package:monveto/models/Dossiermedical%20.dart';
import 'package:monveto/models/TraitementPrescription.dart';

class Consultation {
  final String idConsultation;
  final String syntome;
  final String diagnostic;
  final DateTime dateConsultation;
  final Dossiermedical dossiermedical;
  final List<TraitementPrescription>? traitements;

  Consultation({
    required this.idConsultation,
    required this.syntome,
    required this.diagnostic,
    required this.dateConsultation,
    required this.dossiermedical,
    this.traitements,
  });

  factory Consultation.fromJson(Map<String, dynamic> json) {
    return Consultation(
      idConsultation: json['idConsultation'] as String,
      syntome: json['syntome'] as String,
      diagnostic: json['diagnostic'] as String,
      dateConsultation: DateTime.parse(json['dateConsultation'] as String),
      dossiermedical: Dossiermedical.fromJson(
        json['dossiermedical'] as Map<String, dynamic>,
      ),
      traitements:
          json['traitementPrescriptionList'] != null
              ? (json['traitementPrescriptionList'] as List)
                  .map((e) => TraitementPrescription.fromJson(e))
                  .toList()
              : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'idConsultation': idConsultation,
      'syntome': syntome,
      'diagnostic': diagnostic,
      'dateConsultation': dateConsultation.toIso8601String(),
      'dossiermedical': dossiermedical.toJson(),
      'traitementPrescriptionList':
          traitements?.map((e) => e.toJson()).toList(),
    };
  }

  Consultation copyWith({
    String? idConsultation,
    String? syntome,
    String? diagnostic,
    DateTime? dateConsultation,
    Dossiermedical? dossiermedical,
    List<TraitementPrescription>? traitements,
  }) {
    return Consultation(
      idConsultation: idConsultation ?? this.idConsultation,
      syntome: syntome ?? this.syntome,
      diagnostic: diagnostic ?? this.diagnostic,
      dateConsultation: dateConsultation ?? this.dateConsultation,
      dossiermedical: dossiermedical ?? this.dossiermedical,
      traitements: traitements ?? this.traitements,
    );
  }
}
