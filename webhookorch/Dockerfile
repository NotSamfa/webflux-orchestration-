FROM openjdk:17-jdk-alpine
COPY target/webhookorch-0.0.1-SNAPSHOT.jar docker-webhookorch-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/docker-webhookorch-0.0.1-SNAPSHOT.jar"]
