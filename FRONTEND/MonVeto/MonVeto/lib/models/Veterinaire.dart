
class Veterinaire {
  String? id_veterinaire;
  String? nom_veterinaire;

  String? numero_veterinaire;
  String? mdp_veterinaire;
  String? email_veterinaire;
 


  Veterinaire({
    this.id_veterinaire,
    required this.nom_veterinaire,
    required this.numero_veterinaire,
    required this.mdp_veterinaire,
    required this.email_veterinaire,

   


  });

  factory Veterinaire.fromJson(Map<String, dynamic> json) {
    return Veterinaire(
      id_veterinaire: json['id_veterinaire'] ,
      nom_veterinaire: json['nom_veterinaire'] ?? '',
      numero_veterinaire: json['numero_veterinaire'] ?? '',
      mdp_veterinaire: json['mdp_veterinaire'] ?? '',
      email_veterinaire: json['email_veterinaire'] ?? '',
    
    );
  }



  Map<String, dynamic> toJson(){
    return <String, dynamic>{
      'id_veterinaire':this.id_veterinaire,
      'nom_veterinaire':this.nom_veterinaire,
      'numero_veterinaire':this.numero_veterinaire,
      'mdp_veterinaire':this.mdp_veterinaire,
      'email_veterinaire':this.email_veterinaire,
      
  
    };
  }
}