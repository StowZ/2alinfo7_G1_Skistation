FROM openjdk:8-jdk-alpine
EXPOSE 8089
COPY /Users/nafti/OneDrive/Bureau/repo/target/gestion-station-ski-1.0-SNAPSHOT.jar gestion-station-ski-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/gestion-station-ski-1.0-SNAPSHOT.jar"]
