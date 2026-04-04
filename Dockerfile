FROM gradle:9.4.1-jdk21-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test --no-daemon

FROM openjdk:21-ea-slim-buster
WORKDIR /app
COPY --from=builder /app/target/access-layer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]