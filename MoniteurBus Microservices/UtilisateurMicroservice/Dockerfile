FROM openjdk:17-jdk-alpine
ARG JAR_FILE=UtilisateurMicroservice/target/UtilisateurMicroservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} user.jar
ENTRYPOINT ["java","-jar","/user.jar"]


