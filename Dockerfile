FROM maven:3.9.11-eclipse-temurin-21 AS builder
LABEL authors="a.kherazan"

WORKDIR /app

COPY pom.xml .
COPY application/pom.xml application/
COPY cargo-service/pom.xml cargo-service/
COPY user-service/pom.xml user-service/
COPY contracts/pom.xml contracts/
COPY common/pom.xml common/

RUN --mount=type=cache,target=/root/.m2 \
    mvn dependency:go-offline

COPY . .

RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/application/target/application-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
