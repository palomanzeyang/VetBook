import 'package:monveto/models/Dossiermedical%20.dart';

class Maladie {
  final String idMaladie;
  final String nomMaladie;
  final DateTime dateDiagnostic;
  final String? commentaire;
  final Dossiermedical dossiermedical;

  Maladie({
    required this.idMaladie,
    required this.nomMaladie,
    required this.dateDiagnostic,
    this.commentaire,
    required this.dossiermedical,
  });

  factory Maladie.fromJson(Map<String, dynamic> json) {
    return Maladie(
      idMaladie: json['idMaladie'] as String,
      nomMaladie: json['nomMaladie'] as String,
      dateDiagnostic: DateTime.parse(json['dateDiagnostic'] as String),
      commentaire: json['commentaire'] as String?,
      dossiermedical: Dossiermedical.fromJson(json['dossiermedical'] as Map<String, dynamic>),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'idMaladie': idMaladie,
      'nomMaladie': nomMaladie,
      'dateDiagnostic': dateDiagnostic.toIso8601String(),
      'commentaire': commentaire,
      'dossiermedical': dossiermedical.toJson(),
    };
  }

  Maladie copyWith({
    String? idMaladie,
    String? nomMaladie,
    DateTime? dateDiagnostic,
    String? commentaire,
    Dossiermedical? dossiermedical,
  }) {
    return Maladie(
      idMaladie: idMaladie ?? this.idMaladie,
      nomMaladie: nomMaladie ?? this.nomMaladie,
      dateDiagnostic: dateDiagnostic ?? this.dateDiagnostic,
      commentaire: commentaire ?? this.commentaire,
      dossiermedical: dossiermedical ?? this.dossiermedical,
    );
  }

  @override
  String toString() {
    return 'Maladie(idMaladie: $idMaladie, nomMaladie: $nomMaladie)';
  }
}