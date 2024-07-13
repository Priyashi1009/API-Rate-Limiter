# Set the working directory inside the container
#WORKDIR /app



FROM openjdk:17
LABEL maintainer="api-rate-limiter"
ADD target/apirate-0.0.1-SNAPSHOT.jar app.jar
COPY target/apirate-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]