FROM openjdk:17-jdk-alpine
ARG JAR_FILE=eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} eureka.jar
ENTRYPOINT ["java","-jar","/eureka.jar"]


