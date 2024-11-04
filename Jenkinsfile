pipeline {
    agent any

    stages {
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
    }
}
