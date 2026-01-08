class Message {
  final String idMessage;
  final String contenu;
  final DateTime dateEnvoi;
  final String idVeterinaire;
  final String idProprietaire;

  Message({
    required this.idMessage,
    required this.contenu,
    required this.dateEnvoi,
    required this.idVeterinaire,
    required this.idProprietaire,
  });

  factory Message.fromJson(Map<String, dynamic> json) {
    return Message(
      idMessage: json['idMessage'],
      contenu: json['contenu'],
      dateEnvoi: DateTime.parse(json['dateEnvoi']),
      idVeterinaire: json['veterinaire']['idVeterinaire'],
      idProprietaire: json['proprietaire']['idProprietaire'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'idMessage': idMessage,
      'contenu': contenu,
      'dateEnvoi': dateEnvoi.toIso8601String(),
      'veterinaire': {'idVeterinaire': idVeterinaire},
      'proprietaire': {'idProprietaire': idProprietaire},
    };
  }
}
