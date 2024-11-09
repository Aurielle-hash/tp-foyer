FROM openjdk:17-jdk-alpine
EXPOSE 8082
COPY target/tp-foyer-5.0.0.jar tpfoyer-devops-5.0.0.jar
ENTRYPOINT ["java","-jar","BenHammed/tpfoyer-devops-5.0.0.jar"]