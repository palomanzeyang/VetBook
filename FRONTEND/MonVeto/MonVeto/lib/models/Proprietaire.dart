
class Proprietaire {
  String? idProprietaire;
  String? nomProprietaire;
  String? prenomProprietaire;
  String? adresseProprietaire;
  String? numeroProprietaire;
  String? mdpProprietaire;
  String? emailProprietaire;



  Proprietaire({
    this.idProprietaire,
    required this.nomProprietaire,
    required this.prenomProprietaire,
    required this.adresseProprietaire,
    required this.numeroProprietaire,
    required this.mdpProprietaire,
    required this.emailProprietaire,
    


  });

  factory Proprietaire.fromJson(Map<String, dynamic> json) {
    return Proprietaire(
      idProprietaire: json['idProprietaire'] ,
      nomProprietaire: json['nomProprietaire'] ?? '',
      prenomProprietaire: json['prenomProprietaire'] ?? '',
      adresseProprietaire: json['adresseProprietaire'] ?? '',
      numeroProprietaire: json['numeroProprietaire'] ?? '',
      mdpProprietaire: json['mdpProprietaire'] ?? '',
      emailProprietaire: json['emailProprietaire'] ?? '',
    
    );
  }



  Map<String, dynamic> toJson(){
    return <String, dynamic>{
      'idProprietaire':this.idProprietaire,
      'nomProprietaire':this.nomProprietaire,
      'prenomProprietaire':this.prenomProprietaire,
      'adresseProprietaire':this.adresseProprietaire,
      'numeroProprietaire':this.numeroProprietaire,
      'mdpProprietaire':this.mdpProprietaire,
      'emailProprietaire':this.emailProprietaire,
  
    };
  }
}