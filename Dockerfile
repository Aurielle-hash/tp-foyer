FROM openjdk:23-jre-slim
ARG JAR_FILE=target/tp-foyer-5.0.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]
