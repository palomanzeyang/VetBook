
class Typerendezvous {
  String? id_typerendezvous;
  String nom;

  Typerendezvous({
    this.id_typerendezvous,
    required this.nom,

  });

  factory Typerendezvous.fromJson(Map<String, dynamic> json) {
    return Typerendezvous(
      id_typerendezvous: json['id_typerendezvous'] ,
      nom: json['nom'] ?? '',
    
    );
  }


  Map<String, dynamic> toJson(){
    return <String, dynamic>{
      'id_typerendezvous':this.id_typerendezvous,
      'nom':this.nom,
  
    };
  }
}