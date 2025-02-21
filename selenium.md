### job test interfaces

* on va utiliser un conteneur dynamique avec 
  - une image maven 
  - dans le réseau docker de jenkins
  - en partageant le dépôt de dépendance m2
* on va ajouter un conteneur selenium `selenium/standalone-firefox:133.0-geckodriver-0.35` dans le réseau

*  en manuel
```bash
docker run \
       --name selenium \
       -d --restart unless-stopped \
       selenium/standalone-firefox:133.0-geckodriver-0.35
```

* ou dans gitlab avec un **service**

```yaml
services:
  name: selenium/standalone-firefox:133.0-geckodriver-0.35
  alias: selenium-server
```

* avant de lancer les tests
  - décompresser le driver dans le conteneur
  - l'installer en exécution dans le PATH
  - côté client selenium => utiiser le nom du conteneur
  - utiliser l'option **headless**

* attention à la glue / le resourceclasspath dans `src/test/java/bdd/CucumberTest.java`

* rapports de tests
  - spécifier les formats désirés dans `src/test/resources/junit-platform.properties`
  - ajouter le plugin jenkins **Cucumber reports**