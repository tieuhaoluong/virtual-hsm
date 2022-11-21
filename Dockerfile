FROM openjdk:11

COPY target/virtual-hsm-1.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
