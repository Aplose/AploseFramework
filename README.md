# AploseFramework

AploseFramework est un framework backend conçu pour simplifier et accélérer le développement d'applications mobiles connectées. Ce framework, basé sur **Spring**, offre une intégration fluide avec divers services et ERP tels que **Dolibarr**, ainsi que des solutions de paiement comme **Stripe**, l'authentification (Google, interne, Dolibarr), et la visio-conférence avec **Vizulive**.  

Note : la partie frontend en lien avec ce backend est ici : https://github.com/Aplose/AploseFrameworkNg  

## Caractéristiques

- **API REST** : Expose des services backend pour être consommés par des applications mobiles ou web développées avec **Angular/Ionic**.
- **Intégration facile avec ERP** : Connectez vos applications aux ERP comme **Dolibarr** et d'autres systèmes d'information.
- **Gestion des paiements** : Prend en charge l'intégration de **Stripe** pour des paiements en ligne sécurisés.
- **Authentification flexible** : Authentification via Google, des systèmes internes, ou directement via **Dolibarr**.
- **Visio-conférence** : Intégration avec la plateforme **Vizulive** pour des appels vidéos directement depuis l'application.
- **Extensible** : Facilité d'ajout de nouvelles intégrations selon les besoins des clients.

## Prérequis

- **Java 11+**
- **Maven** pour la gestion des dépendances et la compilation
- Un ERP compatible (ex. Dolibarr) pour l'intégration ERP
- Une instance Stripe pour la gestion des paiements (optionnel)

## Installation

1. Clonez le dépôt :

    ```bash
    git clone https://github.com/votre-utilisateur/AploseFramework.git
    ```

2. Accédez au dossier du projet :

    ```bash
    cd AploseFramework
    ```

3. Compilez le projet avec Maven :

    ```bash
    mvn clean install
    ```

4. Configurez les connexions nécessaires dans les fichiers de configuration (ex. application.properties pour Spring) pour vous connecter à votre ERP, Stripe, Google Auth, etc.

5. Lancez l'application :

    ```bash
    mvn spring-boot:run
    ```

## Utilisation

Une fois l'application lancée, les services backend seront disponibles via des API REST. Vous pouvez les consommer directement via une application mobile **Angular/Ionic** ou tout autre client.

### Exemple de connexion avec l'ERP Dolibarr

Configurez les accès à Dolibarr dans le fichier de configuration, puis utilisez les endpoints définis pour récupérer, mettre à jour, ou créer des entités dans votre ERP.

### Gestion des paiements avec Stripe

Après avoir configuré votre clé API Stripe, utilisez les services fournis pour gérer les transactions, paiements et abonnements de manière sécurisée.

### Authentification Google

Activez Google Auth dans votre application en configurant les identifiants OAuth. Vous pouvez alors authentifier les utilisateurs via Google, en toute sécurité.

## Licence

Ce projet est sous licence MIT. Veuillez consulter le fichier `LICENCE` pour plus de détails.

## Contributions

Les contributions sont les bienvenues ! Si vous souhaitez proposer des améliorations ou corriger des bugs, n'hésitez pas à ouvrir une pull request.
