FROM openjdk:11

RUN mvnw clean package -Dmaven.test.skip

COPY target/virtual-hsm-1.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
