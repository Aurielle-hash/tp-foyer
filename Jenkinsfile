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
                withCredentials([string(credentialsId: '11ea0d21-5ae7-4510-bfdf-6cf8d80558d3',
                                        variable: 'SONAR_TOKEN')]) {
                            sh """
                                mvn sonar:sonar \
                                -Dsonar.host.url=$SONAR_HOST_URL \
                                -Dsonar.login=\$SONAR_TOKEN \
                                -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                            """
                }          //-Dsonar.coverage.jacoco... ici permet à SonarQube de localiser le rapport de couverture JaCoCo généré
            }
        }
       /* stage('Nexus Deploy') {
            steps {
                echo "Déploiement sur Nexus"
                sh "mvn deploy -DskipTests"
            }
        }
        stage('Building image') {
                   steps {
                        echo "creating docker image"
                        sh "docker build -t $Tpfoyer_Image ." //auriel31/tp-foyer:5.0.0
                    }
                }
        stage('Pushing image') {
            steps {
                echo "pushing docker image"
                // Utilise withCredentials pour récupérer les credentials Docker Hub
                withCredentials([usernamePassword(credentialsId: '8b6e20fb-38d6-41ce-a2f5-7a32a513881c',
                                                  usernameVariable: 'DOCKER_USERNAME',
                                                  passwordVariable: 'DOCKER_PASSWORD')]) {
                sh "docker login -u \$DOCKER_USERNAME -p \$DOCKER_PASSWORD" // \$ permet de récupérer la valeur de la variable non lu par Jenkins mais par le shell
                sh "docker push $Tpfoyer_Image"  // "$" va permettre à Jenkins de récupérer la valeur de la variable Tpfoyer_Image
                }
            }
        }
        stage('Debug') {
            steps {
                sh 'docker --version'
                sh 'docker compose version'
                sh 'ls -la' // Pour lister les fichiers dans le répertoire de travail
            }
        }

        stage('Start Docker Composer') {
            steps {
                echo "starting docker composer"
                sh "docker compose down" //arrete le conteneur s'il est deja en cours d'execution

                /*lance le conteneur en arriere plan pour permettre à jenkins
                de continuer la prochaine etape du pipeline sans attendrent que
                 ce service docker se termine et reconstruis les images déjà existantes
                 lorsqu'on a eu à effectuer des modifs dans le code source ou dans dockerfile //
                sh "docker compose up -d --build"
            }
        }
       */
    }
}
