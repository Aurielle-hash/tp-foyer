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
        stage('Building image') {
                   steps {
                        echo "creating docker image"
                        sh "docker build -t auriel31/tp-foyer:5.0.0 ."
                    }
                }
        stage('Pushing image') {
            steps {
                echo "pushing docker image"
                // Utilise withCredentials pour récupérer les credentials Docker Hub
                withCredentials([usernamePassword(credentialsId: '8b6e20fb-38d6-41ce-a2f5-7a32a513881c',
                                                  usernameVariable: 'DOCKER_USERNAME',
                                                  passwordVariable: 'DOCKER_PASSWORD')]) {
                sh "docker login -u \$DOCKER_USERNAME -p \$DOCKER_PASSWORD"
                sh "docker push auriel31/tp-foyer:5.0.0"
                }
            }
        }
    }
}
