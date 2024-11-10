FROM openjdk:17-jdk-alpine
ARG JAR_FILE=/home/maissa/.m2/repository/tn/esprit/tp-foyer/5.0.0/tp-foyer-5.0.0.jar
COPY COPY /home/maissa/.m2/repository/tn/esprit/tp-foyer/5.0.0/tp-foyer-5.0.0.jar /app/tp-foyer-5.0.0.jar/
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/tp-foyer-5.0.0.jar"]
