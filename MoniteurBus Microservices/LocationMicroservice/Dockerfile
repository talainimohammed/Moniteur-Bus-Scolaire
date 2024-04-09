FROM openjdk:17-jdk-alpine
ARG JAR_FILE=LocationMicroservice/target/LocationMicroservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} location.jar
ENTRYPOINT ["java","-jar","/location.jar"]


