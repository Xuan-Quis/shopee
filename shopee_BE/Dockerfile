## build stage ##
FROM maven:3.8.6-eclipse-temurin-17-alpine AS build

WORKDIR /app
COPY . .
RUN mvn install -DskipTests=true

## run stage ##
FROM openjdk:17-alpine


WORKDIR /run
COPY --from=build /app/target/shopee-be-0.0.1-SNAPSHOT.jar /run/shopee-be-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/run/shopee-be-0.0.1-SNAPSHOT.jar"]