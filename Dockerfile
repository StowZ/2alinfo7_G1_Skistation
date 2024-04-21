FROM openjdk:8-alpine
ADD target/*.jar gestion-station-ski-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","gestion-station-ski-1.0-SNAPSHOT.jar"]
