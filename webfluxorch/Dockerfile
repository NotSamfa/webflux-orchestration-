FROM openjdk:17-jdk-alpine
COPY target/webfluxorch-0.0.1-SNAPSHOT.jar docker-webfluxorch-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/docker-webfluxorch-0.0.1-SNAPSHOT.jar"]
