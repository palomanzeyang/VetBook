
class Espece {
  String? id_espece;
  String type;


  Espece({
    this.id_espece,
    required this.type,


  });

  factory Espece.fromJson(Map<String, dynamic> json) {
    return Espece(
      id_espece: json['id_espece'] ?? '',
      type: json['type'] ?? '',
    
    );
  }



  Map<String, dynamic> toJson(){
    return <String, dynamic>{
      'id_espece':this.id_espece,
      'type':this.type,
  
    };
  }
}