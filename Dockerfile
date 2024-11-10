FROM openjdk:17-jdk-alpine
EXPOSE 8082
ADD target/tp-foyer-5.0.0.jar tpfoyer-devops-5.0.0.jar
ENTRYPOINT ["java","-jar","/tpfoyer-devops-5.0.0.jar"]


