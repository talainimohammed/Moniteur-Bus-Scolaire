FROM openjdk:17-jdk-alpine
ARG JAR_FILE=EcoleMicroservice/target/EcoleMicroservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ecole.jar
ENTRYPOINT ["java","-jar","/ecole.jar"]


