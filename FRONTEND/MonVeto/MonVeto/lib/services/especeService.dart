import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:monveto/Constants/Servers.dart';
import 'package:monveto/models/Espece.dart';
import 'package:shared_preferences/shared_preferences.dart';

class EspeceService {
  // Méthode pour récupérer les headers avec le token
  Future<Map<String, String>> _getHeaders() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');
    return {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token',
    };
  }

  Future<List<Espece>> get() async {
    try {
      final headers = await _getHeaders();
      final response = await http.get(
        Uri.parse(AppServer.ESPECE),
        headers: headers,
      );
      
      if (response.statusCode == 200) {
        final List<dynamic> data = jsonDecode(response.body);
        return data.map((json) => Espece.fromJson(json)).toList();
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception("Échec de récupération (${response.statusCode})");
      }
    } catch (e) {
      throw Exception("Erreur lors de la récupération: ${e.toString()}");
    }
  }

  Future<Espece> save(Espece espece) async {
    try {
      final headers = await _getHeaders();
      final response = await http.post(
        Uri.parse(AppServer.ESPECE),
        headers: headers,
        body: jsonEncode(espece.toJson()),
      );
      
      if (response.statusCode == 200 || response.statusCode == 201) {
        return Espece.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception("Échec de l'ajout (${response.statusCode}): ${response.body}");
      }
    } catch (e) {
      throw Exception("Erreur lors de l'ajout: ${e.toString()}");
    }
  }

  Future<Espece> update(String idEspece, Espece espece) async {
    try {
      final headers = await _getHeaders();
      final response = await http.put(
        Uri.parse('${AppServer.ESPECE}/$idEspece'),
        headers: headers,
        body: jsonEncode(espece.toJson()),
      );
      
      if (response.statusCode == 200) {
        return Espece.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception("Échec de la mise à jour (${response.statusCode}): ${response.body}");
      }
    } catch (e) {
      throw Exception("Erreur lors de la mise à jour: ${e.toString()}");
    }
  }

  Future<void> delete(String? idEspece) async {
    try {
      if (idEspece?.trim().isEmpty ?? true) {
        throw Exception("L'ID de l'espèce est requis");
      }

      final headers = await _getHeaders();
      final response = await http.delete(
        Uri.parse('${AppServer.ESPECE}/$idEspece'),
        headers: headers,
      );
      
      if (response.statusCode == 200 || response.statusCode == 204) {
        return;
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception("Échec de la suppression (${response.statusCode}): ${response.body}");
      }
    } catch (e) {
      throw Exception("Erreur lors de la suppression: ${e.toString()}");
    }
  }
}