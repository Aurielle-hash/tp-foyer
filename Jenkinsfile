pipeline {
    agent any
    
    stages {
        stage('Checkout GIT') {
            steps {
            
                echo 'Pulling... '
                git branch: 'reservation',
                credentialsId: '61cdd0f1-76e2-4b38-a151-de18a3fa8ec0',
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
                echo "test avec maven"
                sh "mvn test"
            }
        }
        stage('MVN Sonarqube') {
            steps {
                echo "analyse avec sonarqube"
                sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=admin -Dsonar.password=Sonde56.uses"
            }
        }
        stage('Nexus Deploy') {
            steps {
                echo "Déploiement sur Nexus"
                sh "mvn deploy -DskipTests"
            }
        }
    }
}
