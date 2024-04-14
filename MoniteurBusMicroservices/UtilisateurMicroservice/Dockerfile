# Use an official OpenJDK 17 image as a parent image
FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /app

# Copy any jar file from the target directory into the container at /app
# Here, the jar is renamed to UtilisateursService.jar for clarity
COPY UtilisateurMicroservice/target/*.jar /app/user.jar

# Make port 5560 available to the world outside this container
EXPOSE 5560

# Run the jar file, specifying the renamed jar
ENTRYPOINT ["java", "-jar", "/app/user.jar"]