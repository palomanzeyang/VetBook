import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:monveto/Constants/Servers.dart';
import 'package:monveto/models/Veterinaire.dart';
import 'package:shared_preferences/shared_preferences.dart';

class VeterinaireService {
  // Méthode pour récupérer les headers avec le token
  Future<Map<String, String>> _getHeaders() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');
    return {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token',
    };
  }

  Future<List<Veterinaire>> get() async {
    try {
      final headers = await _getHeaders();
      final response = await http.get(
        Uri.parse(AppServer.VETERINAIRE),
        headers: headers,
      );
      
      if (response.statusCode == 200) {
        final List<dynamic> data = jsonDecode(response.body);
        return data.map((json) => Veterinaire.fromJson(json)).toList();
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de récupération: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur de connexion: $e');
    }
  }

  Future<Veterinaire> save(Veterinaire veterinaire) async {
    try {
      final headers = await _getHeaders();
      final response = await http.post(
        Uri.parse(AppServer.VETERINAIRE),
        headers: headers,
        body: jsonEncode(veterinaire.toJson()),
      );
      
      if (response.statusCode == 200 || response.statusCode == 201) {
        return Veterinaire.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de sauvegarde: ${response.statusCode} - ${response.body}');
      }
    } catch (e) {
      throw Exception('Erreur lors de la sauvegarde: $e');
    }
  }

  Future<Veterinaire> update(String idVeterinaire, Veterinaire veterinaire) async {
    try {
      final headers = await _getHeaders();
      veterinaire.id_veterinaire = idVeterinaire;
      
      final response = await http.put(
        Uri.parse('${AppServer.VETERINAIRE}/$idVeterinaire'),
        headers: headers,
        body: jsonEncode(veterinaire.toJson()),
      );
      
      if (response.statusCode == 200) {
        return Veterinaire.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de mise à jour: ${response.statusCode} - ${response.body}');
      }
    } catch (e) {
      throw Exception('Erreur lors de la mise à jour: $e');
    }
  }

  Future<void> delete(String idVeterinaire) async {
    try {
      final headers = await _getHeaders();
      final response = await http.delete(
        Uri.parse('${AppServer.VETERINAIRE}/$idVeterinaire'),
        headers: headers,
      );
      
      if (response.statusCode == 200 || response.statusCode == 204) {
        return;
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de suppression: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur lors de la suppression: $e');
    }
  }
}