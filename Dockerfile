######## Maven build stage ########
FROM maven:3.6-jdk-11

RUN ls
# build the app (no dependency download here)
RUN mvn -f pom.xml clean package -Dmaven.test.skip

RUN ls

######## JRE run stage ########
FROM openjdk:11

RUN ls

#COPY target/virtual-hsm-1.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

RUN ls

#run the app
ENTRYPOINT ["java", "-jar", "target/virtual-hsm-1.0.1-SNAPSHOT.jar"]
