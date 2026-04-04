FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/access_layer.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]