# TODO: Replace <PROJECT_NAME> below with your project’s name
#       (See Project Explorer in Eclipse)


## Based on https://community.render.com/t/3232

# Build stage

FROM openjdk:11
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Package stage
COPY target/maven-status/*/build/*.jar runningapp.jar
ENTRYPOINT ["java","-jar","/app/runningapp.jar"]
