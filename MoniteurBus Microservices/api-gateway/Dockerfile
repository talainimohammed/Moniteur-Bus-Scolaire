FROM openjdk:17-jdk-alpine
ARG JAR_FILE=api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gateway.jar
ENTRYPOINT ["java","-jar","/gateway.jar"]


