class Rendezvous {
  final String idRendezvous;
  final DateTime dateRendezvous;
  final String objet;
  final String statut;
  final String idAnimal;
  final String idTypeRendezvous;
  final String idVeterinaire;

  Rendezvous({
    required this.idRendezvous,
    required this.dateRendezvous,
    required this.objet,
    required this.statut,
    required this.idAnimal,
    required this.idTypeRendezvous,
    required this.idVeterinaire,
  });

  factory Rendezvous.fromJson(Map<String, dynamic> json) {
    return Rendezvous(
      idRendezvous: json['idRendezvous'],
      dateRendezvous: DateTime.parse(json['dateRendezvous']),
      objet: json['objet'],
      statut: json['statut'],
      idAnimal: json['animal']['idAnimal'],
      idTypeRendezvous: json['typerendezvous']['idTyperendezvous'],
      idVeterinaire: json['veterinaire']['idVeterinaire'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'idRendezvous': idRendezvous,
      'dateRendezvous': dateRendezvous.toIso8601String(),
      'objet': objet,
      'statut': statut,
      'animal': {'idAnimal': idAnimal},
      'typerendezvous': {'idTyperendezvous': idTypeRendezvous},
      'veterinaire': {'idVeterinaire': idVeterinaire},
    };
  }
}
