######## Maven build stage ########
FROM maven:3.6-jdk-11 as maven_build

# build the app (no dependency download here)
RUN mvn clean package -Dmaven.test.skip


######## JRE run stage ########
FROM openjdk:11

COPY target/virtual-hsm-1.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

#run the app
ENTRYPOINT ["java", "-jar", "/app.jar"]
