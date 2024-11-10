FROM openjdk:17-jdk-alpine
ARG JAR_FILE=/home/maissa/devops/TPS/tp-foyer/target/tp-foyer-5.0.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]
