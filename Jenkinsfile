pipeline {
    agent { label 'build_slaves' }

    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling... '
                git branch: 'etudient',
                    url: 'https://github.com/Aurielle-hash/tp-foyer.git',
                    credentialsId: '10dfc7a7-f955-43d9-80a5-a848bcf7c1f2' 
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
                echo "Compilation avec maven"
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

