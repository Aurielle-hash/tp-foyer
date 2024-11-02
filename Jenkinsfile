pipeline {
    agent any 

    stages {
        stage('Build') {
                 stage('Checkout GIT') {
            steps {
            
                echo 'Pulling... '
                checkout scm
            }
        }
  
        stage('Maven Clean') {
            steps {
               echo "Clean avec maven"
               
               sh "mvn clean"

            }

          
        }
        stage('Maven Compile') {
            steps {
                echo "compilation avec maven"
                sh "mvn compile"
            }
        }
        stage('MVN Test') {
            steps {
                echo "test avec maven"
                sh "mvn test"
            }
    }
}

