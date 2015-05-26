# Beispiel(e) für Docker-Talk

## Übersicht
- Container mit HAProxy
- mehrere Container mit Microservice
- optional: Container mit Datenbank

## Control
- ```cluster start``` startet alle Container
- ```cluster stop``` beendet && zerstört alle Container

## Zugriff

http://localhost:8080/hello-world?name=me

## Bauen des Java-Webgedöns

cd java && mvn package && docker build -t exxcellent/docker_talk .
