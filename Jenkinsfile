pipeline {
    agent any 

    stages {
        stage('Checkout GIT') {
       steps {
            
                echo 'Pulling... '
                git branch: 'master',
                url :'https://github.com/Aurielle-hash/tp-foyer.git'
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
                echo "Test avec maven"
                sh "mvn test"
            }
        }
    }
}

