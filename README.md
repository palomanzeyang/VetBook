# 🐾 VetBook Management System

*Backend Spring Boot • Frontend Flutter • Sécurité JWT*

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-green.svg)](https://spring.io/projects/spring-boot)
[![Flutter](https://img.shields.io/badge/Flutter-3.16-blue.svg)](https://flutter.dev)
[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://openjdk.org/projects/jdk/17/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-orange.svg)](https://www.mysql.com)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## 📖 Description

Plateforme tout-en-un pour la gestion médicale des animaux, comprenant :
- **Backend** : API REST sécurisée avec Spring Boot
- **Frontend** : Application mobile multiplateforme Flutter
- **Fonctionnalités clés** : Dossiers médicaux, rendez-vous, messagerie sécurisée

Destiné aux vétérinaires et propriétaires d'animaux pour simplifier la gestion des soins vétérinaires.

## 🛠️ Fonctionnalités Techniques

### 🔙 Backend Spring Boot
- **Gestion des dossiers médicaux** 
  - Historique complet des vaccins, maladies et traitements
  - Suivi des prescriptions médicales
  - Stockage des résultats d'analyses

- **Gestion des rendez-vous**
  - Prise de rendez-vous en ligne
  - Système de rappel automatique
  - Calendrier de disponibilité des vétérinaires

- **Communication**
  - Messagerie sécurisée propriétaire-vétérinaire
  - Notifications push pour les rappels importants

- **Sécurité**
  - Authentification JWT sécurisée
  - Utilisateurs (Vétérinaire/Propriétaire)
  - Chiffrement des données sensibles

### 📱 Frontend Flutter
**Pour les propriétaires :**
- 📅 Prise de rendez-vous intuitive
- 🏥 Accès à l'historique médical complet
- 🔔 Notifications pour vaccins et traitements
- 💬 Chat en direct avec le vétérinaire

**Pour les vétérinaires :**
- 🐕 Gestion centralisée des patients
- 💊 Système de prescription numérique
- 📊 Tableau de bord analytique
- 📱 Accès mobile aux dossiers

## 🛠 Stack Technique

### Backend (Spring Boot 3.4.3)
```xml
<!-- Dépendances principales -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
```

### Frontend (Flutter 3.16)
```yaml
dependencies:
  flutter:
    sdk: flutter
  http: ^1.3.0                    # Pour les appels API
  shared_preferences: ^2.2.0      # Stockage local
  fl_chart: ^0.71.0               # Graphiques
  intl: ^0.20.2                   # Internationalisation

dev_dependencies:
  build_runner: ^2.4.6            # Génération de code
  json_serializable: ^6.7.1       # Sérialisation JSON
```

## 🚀 Installation Locale

### Prérequis
- **Backend** : JDK 17+, MySQL 8+, Maven 3.6+
- **Frontend** : Flutter 3.0+, Android Studio/Xcode

### 🔙 Configuration Backend
1. **Cloner et configurer** :
   ```bash
   git clone https://github.com/votre-repo/gestion-animaux.git
   cd backend
   ```
   
2. **Base de données** :
   ```bash
   mysql -u root -p < src/main/resources/schema.sql
   ```

3. **Lancer le serveur** :
   ```bash
   mvn spring-boot:run
   ```
   *API disponible sur http://localhost:8080*

### 📱 Configuration Frontend
1. **Installer les dépendances** :
   ```bash
   cd frontend
   flutter pub get
   ```

2. **Adapter les endpoints** (`lib/constants/app_server.dart`) :
   ```dart
   // Configuration multi-environnement
   abstract class AppServer {
     static const API = String.fromEnvironment('API_URL', 
       defaultValue: "http://10.0.2.2:8080"); // Android par défaut
     
     static const ANIMAL = "$API/animal";
     static const LOGIN = "$API/api/auth/signin";
   }
   ```

3. **Lancer l'application** :
   ```bash
   flutter run --dart-define=API_URL=http://votre_ip:8080
   ```

## 🔍 Tester les API

### Méthodes recommandées
| Outil          | Commande                      | Cas d'usage          |
|----------------|-------------------------------|----------------------|
| **Postman**    | Importez [notre collection](docs/postman.json) | Tests manuels        |


## 📊 Architecture Frontend
```
lib/
├── models/          # Modèles de données
├── services/        # Services API
├── constants/       # Configuration
├── widgets/         # Composants UI
└── screens/         # Écrans principaux
```

## 📚 Documentation Complète
- [API Reference](docs/api.md) - Documentation OpenAPI 3.0
- [Frontend Guide](docs/frontend_guide.md) - Architecture Flutter
- [Deployment](docs/deployment.md) - Guide de déploiement en production

## 🤝 Bonnes Pratiques
- **Backend** : 
  ```bash
  # Formatage du code
  mvn spotless:apply
  ```
- **Frontend** :
  ```bash
  # Génération des modèles JSON
  flutter pub run build_runner build
  ```

## 📄 Licence
MIT License - Voir [LICENSE](LICENSE) pour plus de détails
