FROM openjdk:17-jdk-alpine
EXPOSE 8082
<<<<<<< HEAD
ADD target/tp-foyer-5.0.0.jar tpfoyer-devops-5.0.0.jar
ENTRYPOINT ["java","-jar","/tpfoyer-devops-5.0.0.jar"]
=======
ADD target/tp-foyer-5.0.0.jar tpfoyer-devops-5.0.0.jar
ENTRYPOINT ["java","-jar","/tpfoyer-devops-5.0.0.jar"]
>>>>>>> origin/etudient
