FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/gestion-station-ski-1.0-SNAPSHOT.jar gestion-station-ski-1.0-SNAPSHOT
ENTRYPOINT ["java","-jar","/gestion-station-ski-1.0-SNAPSHOT"]