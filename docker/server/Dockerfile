FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /server

COPY mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw

COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /server
ARG JAR_FILE=flightcoordinator-server.jar
COPY --from=build /server/target/${JAR_FILE} /server/flightcoordinator-server.jar

ENV SERVER_PORT=8081

EXPOSE ${SERVER_PORT}
ENTRYPOINT [ "java", "-jar", "/server/flightcoordinator-server.jar" ]
