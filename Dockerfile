FROM openjdk:17-jdk-alpine
ARG JAR_FILE=/home/maissa/.m2/repository/tn/esprit/tp-foyer/5.0.0/tp-foyer-5.0.0.jar
ADD/home/maissa/devops/TPS/tp-foyer/target/tp-foyer-5.0.0.jar app/tp-foyer-5.0.0.jar/
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/tp-foyer-5.0.0.jar"]
