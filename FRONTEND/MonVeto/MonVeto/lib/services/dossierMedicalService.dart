import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:monveto/Constants/Servers.dart';
import 'package:monveto/models/Consultation.dart';
import 'package:monveto/models/Dossiermedical%20.dart';

import 'package:monveto/models/Maladie.dart';
import 'package:monveto/models/Vaccin.dart';
import 'package:shared_preferences/shared_preferences.dart';

class DossierMedicalService {
  final String _baseUrl = AppServer.DOSSIERMEDICAL;

  // Méthode pour récupérer les en-têtes avec le token d'authentification à jour
  Future<Map<String, String>> _getHeaders() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');
    return {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token',
    };
  }

  Future<Dossiermedical> create(Dossiermedical dossier) async {
    try {
      final headers = await _getHeaders();
      final response = await http.post(
        Uri.parse(_baseUrl),
        headers: headers,
        body: jsonEncode(dossier.toJson()),
      );

      if (response.statusCode == 201 || response.statusCode == 200) {
        return Dossiermedical.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de création du dossier: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }

  Future<Dossiermedical?> getById(String id) async {
    try {
      final headers = await _getHeaders();
      final response = await http.get(
        Uri.parse('$_baseUrl/$id'),
        headers: headers,
      );

      if (response.statusCode == 200) {
        return Dossiermedical.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 404) {
        return null; // Aucun dossier trouvé
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec du chargement du dossier: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }

  Future<List<Dossiermedical>> getByAnimalId(String animalId) async {
    try {
      final headers = await _getHeaders();
      final response = await http.get(
        Uri.parse('$_baseUrl/animal/$animalId'),
        headers: headers,
      );

      if (response.statusCode == 200) {
        final List<dynamic> data = jsonDecode(response.body);
        return data.map((json) => Dossiermedical.fromJson(json)).toList();
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec du chargement des dossiers: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }

  Future<Dossiermedical> update(String id, Dossiermedical dossier) async {
    try {
      final headers = await _getHeaders();
      final response = await http.put(
        Uri.parse('$_baseUrl/$id'),
        headers: headers,
        body: jsonEncode(dossier.toJson()),
      );

      if (response.statusCode == 200) {
        return Dossiermedical.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de la mise à jour du dossier: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }

  Future<void> delete(String id) async {
    try {
      final headers = await _getHeaders();
      final response = await http.delete(
        Uri.parse('$_baseUrl/$id'),
        headers: headers,
      );

      if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else if (response.statusCode != 200 && response.statusCode != 204) {
        throw Exception('Échec de la suppression du dossier: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }

  Future<Dossiermedical> addVaccin(String dossierId, Vaccin vaccin) async {
    try {
      final headers = await _getHeaders();
      final response = await http.post(
        Uri.parse('$_baseUrl/$dossierId/vaccin'),
        headers: headers,
        body: jsonEncode(vaccin.toJson()),
      );

      if (response.statusCode == 200) {
        return Dossiermedical.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de l\'ajout du vaccin: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }

  Future<Dossiermedical> addConsultation(String dossierId, Consultation consultation) async {
    try {
      final headers = await _getHeaders();
      final response = await http.post(
        Uri.parse('$_baseUrl/$dossierId/consultation'),
        headers: headers,
        body: jsonEncode(consultation.toJson()),
      );

      if (response.statusCode == 200) {
        return Dossiermedical.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de l\'ajout de la consultation: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }

  Future<Dossiermedical> addMaladie(String dossierId, Maladie maladie) async {
    try {
      final headers = await _getHeaders();
      final response = await http.post(
        Uri.parse('$_baseUrl/$dossierId/maladie'),
        headers: headers,
        body: jsonEncode(maladie.toJson()),
      );

      if (response.statusCode == 200) {
        return Dossiermedical.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de l\'ajout de la maladie: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }
  
  // Méthodes pour supprimer des éléments du dossier
  Future<Dossiermedical> removeVaccin(String dossierId, String vaccinId) async {
    try {
      final headers = await _getHeaders();
      final response = await http.delete(
        Uri.parse('$_baseUrl/$dossierId/vaccin/$vaccinId'),
        headers: headers,
      );

      if (response.statusCode == 200) {
        return Dossiermedical.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de la suppression du vaccin: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }

  Future<Dossiermedical> removeConsultation(String dossierId, String consultationId) async {
    try {
      final headers = await _getHeaders();
      final response = await http.delete(
        Uri.parse('$_baseUrl/$dossierId/consultation/$consultationId'),
        headers: headers,
      );

      if (response.statusCode == 200) {
        return Dossiermedical.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de la suppression de la consultation: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }

  Future<Dossiermedical> removeMaladie(String dossierId, String maladieId) async {
    try {
      final headers = await _getHeaders();
      final response = await http.delete(
        Uri.parse('$_baseUrl/$dossierId/maladie/$maladieId'),
        headers: headers,
      );

      if (response.statusCode == 200) {
        return Dossiermedical.fromJson(jsonDecode(response.body));
      } else if (response.statusCode == 401) {
        throw Exception('Session expirée. Veuillez vous reconnecter.');
      } else {
        throw Exception('Échec de la suppression de la maladie: ${response.statusCode}');
      }
    } catch (e) {
      throw Exception('Erreur: $e');
    }
  }
}