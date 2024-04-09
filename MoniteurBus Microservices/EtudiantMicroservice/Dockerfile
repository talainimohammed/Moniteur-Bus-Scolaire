FROM openjdk:17-jdk-alpine
ARG JAR_FILE=EtudiantMicroservice/target/EtudiantMicroservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} etudiant.jar
ENTRYPOINT ["java","-jar","/etudiant.jar"]


