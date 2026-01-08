import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';

class AuthInterceptor extends http.BaseClient {
  final http.Client _inner = http.Client();
  final String authUrl = 'http://localhost:8080/api/auth/refresh'; // Endpoint de rafraîchissement du token

  Future<http.StreamedResponse> send(http.BaseRequest request) async {
    // Ajout du token à la requête
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString('token');
    
    if (token != null) {
      request.headers['Authorization'] = 'Bearer $token';
    }

    // Envoi de la requête
    http.StreamedResponse response = await _inner.send(request);

    // Si on reçoit un 401, on tente de rafraîchir le token
    if (response.statusCode == 401 && !request.url.path.contains('/api/auth/login')) {
      try {
        // Tentative de rafraîchissement du token
        String? refreshToken = prefs.getString('refreshToken');
        if (refreshToken == null) {
          // Si pas de refresh token, on ne peut pas rafraîchir
          return response;
        }

        final refreshResponse = await _inner.post(
          Uri.parse(authUrl),
          headers: {'Content-Type': 'application/json'},
          body: jsonEncode({'refreshToken': refreshToken}),
        );

        if (refreshResponse.statusCode == 200) {
          // Stockage du nouveau token
          final Map<String, dynamic> data = jsonDecode(refreshResponse.body);
          await prefs.setString('token', data['token']);
          
          // Création d'une nouvelle requête avec le token actualisé
          var newRequest = _copyRequest(request);
          newRequest.headers['Authorization'] = 'Bearer ${data['token']}';
          
          // Envoi de la nouvelle requête
          return await _inner.send(newRequest);
        }
      } catch (e) {
        print('Erreur lors du rafraîchissement du token: $e');
      }
    }

    return response;
  }

  // Méthode helper pour copier une requête
  http.BaseRequest _copyRequest(http.BaseRequest request) {
    http.BaseRequest newRequest;
    
    if (request is http.Request) {
      newRequest = http.Request(request.method, request.url)
        ..encoding = request.encoding
        ..bodyBytes = request.bodyBytes;
    } else if (request is http.MultipartRequest) {
      newRequest = http.MultipartRequest(request.method, request.url)
        ..fields.addAll(request.fields)
        ..files.addAll(request.files);
    } else {
      newRequest = http.Request(request.method, request.url);
    }

    newRequest.headers.addAll(request.headers);
    newRequest.followRedirects = request.followRedirects;
    newRequest.persistentConnection = request.persistentConnection;
    
    return newRequest;
  }
}