FROM openjdk:17-jdk-alpine
COPY target/batchOrches-0.0.1-SNAPSHOT.jar docker-batchOrches-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/docker-batchOrches-0.0.1-SNAPSHOT.jar"]
