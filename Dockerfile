# Fase Build
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests package

# Fase Runtime
FROM eclipse-temurin:21-jre
ENV APP_HOME=/opt/app

WORKDIR ${APP_HOME}

# crea user no-root
RUN useradd -ms /bin/bash appuser
USER appuser

# copiar el jar
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

EXPOSE 8080 8000

ENTRYPOINT ["java","-jar","app.jar"]