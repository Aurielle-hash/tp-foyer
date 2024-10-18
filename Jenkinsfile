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
        stage('MVN Sonarqube') {
            steps {
                echo "analyse avec sonarqube"
                sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=admin -Dsonar.password=Admin/sonar12"
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
