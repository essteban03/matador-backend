# Multi-stage build for Render (Spring Boot en la raíz de este repo)
# Debe coincidir con <java.version> en pom.xml (ahora 21).
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080
# Render a veces no tiene ruta IPv6; db.*.supabase.co puede resolver solo a IPv6 y falla con
# "Network is unreachable". Forzar IPv4 en la JVM suele arreglarlo sin cambiar de host.
ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "app.jar"]
