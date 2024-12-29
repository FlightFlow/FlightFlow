FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /server

COPY mvnw .
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

RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup
USER appuser

EXPOSE 8081
ENTRYPOINT [ "java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75", "-jar", "/server/flightcoordinator-server.jar" ]
