import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:monveto/Constants/Servers.dart';
import 'package:monveto/models/Animal.dart';
import 'package:shared_preferences/shared_preferences.dart';

class Animalservice {
  // Méthode pour récupérer les en-têtes avec le token d'authentification à jour
  Future<Map<String, String>> _getHeaders() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');
    return {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token',
    };
  }

  Future<List<Animal>> getAnimals() async {
    try {
      final headers = await _getHeaders();
      final response = await http.get(
        Uri.parse('${AppServer.ANIMAL}/all'),
        headers: headers,
      );
      
      if (response.statusCode == 200) {
        final List<dynamic> data = jsonDecode(response.body);
        return data.map((json) => Animal.fromJson(json)).toList();
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec du chargement: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur de connexion: $e');
    }
  }

  Future<List<Map<String, dynamic>>> getEspeces() async {
    try {
      final headers = await _getHeaders();
      final response = await http.get(
        Uri.parse('${AppServer.ESPECE}/all'),
        headers: headers,
      );
      
      if (response.statusCode == 200) {
        final List<dynamic> data = jsonDecode(response.body);
        return List<Map<String, dynamic>>.from(data);
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec chargement espèces: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur connexion: $e');
    }
  }

  Future<List<Map<String, dynamic>>> getProprietaires() async {
    try {
      final headers = await _getHeaders();
      final response = await http.get(
        Uri.parse('${AppServer.PROPRIETAIRE}/all'),
        headers: headers,
      );
      
      if (response.statusCode == 200) {
        final List<dynamic> data = jsonDecode(response.body);
        return List<Map<String, dynamic>>.from(data);
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec chargement propriétaires: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur connexion: $e');
    }
  }

  Future<List<Map<String, dynamic>>> getVeterinaires() async {
    try {
      final headers = await _getHeaders();
      final response = await http.get(
        Uri.parse('${AppServer.VETERINAIRE}/all'),
        headers: headers,
      );
      
      if (response.statusCode == 200) {
        final List<dynamic> data = jsonDecode(response.body);
        return List<Map<String, dynamic>>.from(data);
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Erreur chargement vétérinaires: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur connexion: $e');
    }
  }

  Future<Animal> saveOrUpdateAnimal(Animal animal) async {
    try {
      final headers = await _getHeaders();
      final method = animal.idAnimal.isEmpty ? 'POST' : 'PUT';
      final url = '${AppServer.ANIMAL}/saveOrUpdate';
      
      // Adapter le format pour correspondre à ce que le backend attend
      final requestBody = {
        'idAnimal': animal.idAnimal.isEmpty ? null : animal.idAnimal,
        'nomAnimal': animal.nomAnimal,
        'ageAnimal': animal.ageAnimal,
        'sexeAnimal': animal.sexeAnimal,
        'couleurAnimal': animal.couleurAnimal,
        'idEspece': animal.especeId,
        'idProprietaire': animal.proprietaireId,
        'idVeterinaire': animal.veterinaireId,
      };
      
      final response = await (method == 'POST' ? http.post : http.put)(
        Uri.parse(url),
        headers: headers,
        body: jsonEncode(requestBody),
      );
      
      if (response.statusCode == 200 || response.statusCode == 201) {
        // Extraire le résultat et le convertir en objet Animal
        final responseData = jsonDecode(response.body);
        return Animal(
          idAnimal: responseData['idAnimal'] ?? '',
          nomAnimal: responseData['nomAnimal'] ?? '',
          ageAnimal: responseData['ageAnimal'] ?? '',
          sexeAnimal: responseData['sexeAnimal'] ?? '',
          couleurAnimal: responseData['couleurAnimal'] ?? '',
          especeId: responseData['idEspece'] ?? '',
          especeNom: animal.especeNom, // Conserver le nom existant
          proprietaireId: responseData['idProprietaire'] ?? '',
          proprietaireNom: animal.proprietaireNom, // Conserver le nom existant
          veterinaireId: responseData['idVeterinaire'],
          veterinaireNom: animal.veterinaireNom, // Conserver le nom existant
        );
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec ${method == 'POST' ? 'sauvegarde' : 'mise à jour'}: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }

  Future<void> deleteAnimal(String id) async {
    try {
      final headers = await _getHeaders();
      final response = await http.delete(
        Uri.parse('${AppServer.ANIMAL}/$id'),
        headers: headers,
      );
      
      if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else if (response.statusCode != 200 && response.statusCode != 204) {
        throw Exception('Échec suppression: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }
  
  Future<Map<String, dynamic>> getAnimalDetails(String id) async {
    try {
      final headers = await _getHeaders();
      final response = await http.get(
        Uri.parse('${AppServer.ANIMAL}/details/$id'),
        headers: headers,
      );
      
      if (response.statusCode == 200) {
        return jsonDecode(response.body);
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec chargement détails: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur connexion: $e');
    }
  }
}