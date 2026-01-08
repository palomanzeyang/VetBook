import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:monveto/Constants/Servers.dart';
import 'package:monveto/models/Proprietaire.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ProprietaireService {
  // Méthode pour récupérer les headers avec le token
  Future<Map<String, String>> _getHeaders() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');
    return {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token',
    };
  }

  Future<List<Proprietaire>> get() async {
    try {
      final headers = await _getHeaders();
      final response = await http.get(
        Uri.parse(AppServer.PROPRIETAIRE),
        headers: headers,
      );
      
      if (response.statusCode == 200) {
        final List<dynamic> data = jsonDecode(response.body);
        return data.map((json) => Proprietaire.fromJson(json)).toList();
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de récupération: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur de connexion: $e');
    }
  }

  Future<Proprietaire> save(Proprietaire proprietaire) async {
    try {
      final headers = await _getHeaders();
      final response = await http.post(
        Uri.parse(AppServer.PROPRIETAIRE),
        headers: headers,
        body: jsonEncode(proprietaire.toJson()),
      );
      
      if (response.statusCode == 200 || response.statusCode == 201) {
        return Proprietaire.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de sauvegarde: ${response.statusCode} - ${response.body}');
      }
    } catch (e) {
      throw Exception('Erreur lors de la sauvegarde: $e');
    }
  }

  Future<Proprietaire> update(String idProprietaire, Proprietaire proprietaire) async {
    try {
      final headers = await _getHeaders();
      proprietaire.idProprietaire = idProprietaire;
      
      final response = await http.put(
        Uri.parse('${AppServer.PROPRIETAIRE}/$idProprietaire'),
        headers: headers,
        body: jsonEncode(proprietaire.toJson()),
      );
      
      if (response.statusCode == 200) {
        return Proprietaire.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de mise à jour: ${response.statusCode} - ${response.body}');
      }
    } catch (e) {
      throw Exception('Erreur lors de la mise à jour: $e');
    }
  }

  Future<void> delete(String idProprietaire) async {
    try {
      final headers = await _getHeaders();
      final response = await http.delete(
        Uri.parse('${AppServer.PROPRIETAIRE}/$idProprietaire'),
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