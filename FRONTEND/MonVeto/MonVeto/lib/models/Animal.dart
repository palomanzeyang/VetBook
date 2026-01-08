class Animal {
  String idAnimal;
  String nomAnimal;
  String ageAnimal;
  String sexeAnimal;
  String couleurAnimal;
  String especeId;
  String especeNom;
  String proprietaireId;
  String proprietaireNom;
  String? veterinaireId;
  String? veterinaireNom;

  Animal({
    required this.idAnimal,
    required this.nomAnimal,
    required this.ageAnimal,
    required this.sexeAnimal,
    required this.couleurAnimal,
    required this.especeId,
    required this.especeNom,
    required this.proprietaireId,
    required this.proprietaireNom,
    this.veterinaireId,
    this.veterinaireNom,
  });

  factory Animal.fromJson(Map<String, dynamic> json) {
    return Animal(
      idAnimal: json['idAnimal'] ?? '',
      nomAnimal: json['nomAnimal'] ?? '',
      ageAnimal: json['ageAnimal'] ?? '',
      sexeAnimal: json['sexeAnimal'] ?? '',
      couleurAnimal: json['couleurAnimal'] ?? '',
      especeId: json['idEspece'] ?? '',
      especeNom: json['especeNom'] ?? '',
      proprietaireId: json['idProprietaire'] ?? '',
      proprietaireNom: json['proprietaireNom'] ?? '',
      veterinaireId: json['idVeterinaire'],
      veterinaireNom: json['veterinaireNom'],
    );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    // Ne pas inclure idAnimal si c'est un nouvel animal (chaîne vide)
    if (idAnimal.isNotEmpty) {
      data['idAnimal'] = idAnimal;
    }
    data['nomAnimal'] = nomAnimal;
    data['ageAnimal'] = ageAnimal;
    data['sexeAnimal'] = sexeAnimal;
    data['couleurAnimal'] = couleurAnimal;
    data['idEspece'] = especeId;
    data['idProprietaire'] = proprietaireId;
    if (veterinaireId != null && veterinaireId!.isNotEmpty) {
      data['idVeterinaire'] = veterinaireId;
    }
    return data;
  }
}