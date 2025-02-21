* stage QUALITY
  - effectuer une analyse statique de code (SAST) Static Application Security Testing GRATUIT
  - vs DAST Dynamic => analyse *in vivo* / analyse de conformité aux standards PAYANT
  - vs pentesting => tests d'intrusion => simuler une attaque (juridique) DIY (kali linux)

* outils sonarQube de SonarSource
  - outils client (sonarscanner) / serveur (sonarQube)
  - côté client : goal `mvn sonar` ajouté par le plugin **sonar-maven-plugin** dans le pom
  - côté serveur : conteneur sonarQube (cf infra)

#### setup

1. création du conteneur sonarqube

```bash
docker run \
    --name sonar \
    -d --restart unless-stopped \
    -p 9000:9000 \
    --memory 2g \
    sonarqube:lts
```

   * REM: ajout du CGROUP memory pour limiter l'utilisation de la RAM au conteneur sonarqube, il faut ajouter le -xmx de la jvm dans le conteneur sonarqube

2. création et configuration d'un projet sonar avec un profile qualité et une gate

   * `http://gitlab.lan.fr:9000` > admin / admin
   * change pour admin / roottoor
   * config manuelle
   * nom du projet : java-app
   * analyse: manuelle
   * token: sqp_134a4f503d3b5c19a603a07d1aa61a9d45cac250
   * snippet a priori de la connexion

   ```bash
   mvn clean verify sonar:sonar \
      -Dsonar.projectKey=java-app \
      -Dsonar.host.url=http://gitlab.lan:9000 \
      -Dsonar.login=sqp_134a4f503d3b5c19a603a07d1aa61a9d45cac250
   ```

   * création du profile qualité
      - java (profile par défaut)
      - copie (activation/désactivation de règles) vs extension (activation de règles)
      - attache le profile au projet
  
   *  création d'une "gate" > seuil de validation d'acceptabilité de l'analyse
      - gate par défaut pour le nouveau code
      - customisation des critères
      - certains critères ne peut être calculés directement par SonarQube par ex. coverage
   
   * test en local
     - ajouter les propriétés
       + **sonar.host.url**
       + **sonar.projectKey**
       + **sonar.login**
     - exécuter le maven sonar:sonar avec l'EDI ou
     - `mvn sonar:sonar -f "c:\Users\<user>\<path>\workdir\pom.xml"`