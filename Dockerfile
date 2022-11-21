## Build stage ##

#FROM maven:3.6-jdk-11 AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip

## Package stage ##

#FROM openjdk:11
#COPY --from=build /home/app/target/virtual-hsm-1.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]


######## Maven build stage ########
FROM maven:3.6-jdk-11 as build

COPY src /home/app/src
COPY pom.xml /home/app

# build the app (no dependency download here)
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip

######## JRE run stage ########
FROM openjdk:11

RUN ls

COPY --from=build /home/app/target/virtual-hsm-1.0.1-SNAPSHOT.jar /home/app/app.jar

EXPOSE 8080

RUN ls

#run the app
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]
