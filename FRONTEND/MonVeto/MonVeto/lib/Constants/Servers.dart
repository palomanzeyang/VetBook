class AppServer {
  // Pour le développement local
  // static const API = "http://localhost:8080";
  
  // Pour accéder à l'API dockerisée depuis l'extérieur
  static const API = "http://localhost:8080";
  
  // Pour les tests sur un appareil physique ou émulateur
  // static const API = "http://192.168.1.X:8080"; // Remplacez X par votre adresse IP locale
  
  static const ESPECE = API + "/espece";
  static const PROPRIETAIRE = API + "/api/auth/proprietaire";
  static const VETERINAIRE = API + "/api/auth/veterinaire";
  static const ANIMAL = API + "/animal";
  static const DOSSIERMEDICAL = API + "/dossiermedical";
  static const RENDEZVOUS = API + "/rendezvous";
  
  static Map<String, String> getHeaders({String? token}) {
    Map<String, String> headers = {
      'Content-Type': 'application/json',
      "Access-Control-Allow-Origin": "*",
      'Accept': 'application/json',
    };
    
    if (token != null) {
      headers['Authorization'] = 'Bearer $token';
    }
    
    return headers;
  }
}