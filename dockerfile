FROM openjdk:17-jdk-alpine 
ARG JAR_FILE=target/*.jar 
COPY target/*.jar gestion-station-ski-1.0-SNAPSHOT
ENTRYPOINT ["java","-jar","gestion-station-ski-1.0-SNAPSHOT"]
