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
                dir('tp-foyer') {
                    sh 'mvn clean'
                }
            }

        }

        stage('Maven Compile') {
            steps {
                echo "compilation avec maven"
                dir('tp-foyer') {
                    sh 'mvn compile'
                }
            }
        }

        stage('MOCKITO Test') {
            steps {
                echo "test avec maven"
                dir('tp-foyer') {
                    sh 'mvn test'
                }
            }
        }

        stage('MVN Sonarqube') {
            steps {
                echo "analyse avec sonarqube"
                dir('tp-foyer') {
                    withCredentials([string(credentialsId: '11ea0d21-5ae7-4510-bfdf-6cf8d80558d3',
                                        variable: 'SONAR_TOKEN')]) {
                        sh """
                        mvn sonar:sonar \
                        -Dsonar.host.url=$SONAR_HOST_URL \
                        -Dsonar.login=\$SONAR_TOKEN
                        """
                    }
                }
            }
        }

        stage('Nexus Deploy') {
            steps {
                echo "Déploiement sur Nexus"
                dir('tp-foyer') {
                    withCredentials([usernamePassword(credentialsId: 'bcc1b017-d8af-459d-883d-133048e255b8',
                                                   usernameVariable: 'NEXUS_USERNAME',
                                                   passwordVariable: 'NEXUS_PASSWORD')]) {
                        sh """
                        mvn deploy \
                        -DskipTests \
                        -Dusername=\$NEXUS_USERNAME \
                        -Dpassword=\$NEXUS_PASSWORD
                        """
                    }
                }
            }
        }

        stage('Building backend image') {
            steps {
                echo "creating backend docker image"
                dir('tp-foyer') {
                    sh "docker build -f Dockerfile -t $BACKEND_IMAGE ."
                }
            }
        }

        stage('Pushing image backend') {
            steps {
                echo "Push docker image backend"
                        // Utilise withCredentials pour récupérer les credentials Docker Hub
                withCredentials([usernamePassword(credentialsId: '8b6e20fb-38d6-41ce-a2f5-7a32a513881c',
                                                  usernameVariable: 'DOCKER_USERNAME',
                                                  passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "docker login -u \$DOCKER_USERNAME -p \$DOCKER_PASSWORD" // \$ permet de récupérer la valeur de la variable non lu par Jenkins mais par le shell
                    sh "docker push $BACKEND_IMAGE"  // "$" va permettre à Jenkins de récupérer la valeur de la variable BACKEND_IMAGE


                }
            }
        }

        stage('Start Docker Composer backend') {
            steps {
                echo "starting docker composer"
                //arrete le conteneur s'il est deja en cours d'execution
                sh 'docker compose down'
                sh 'docker compose up -d --build'
            }
        }

       /*  stage('building frontend image') {
            steps {
                echo "creating frontend docker image"
                dir('tp-foyer-frontend') {
                //  build avec cache pour eviter de retelecharger les dependances

                    sh "docker build --cache-from=$FRONTEND_IMAGE -f Dockerfile-angular -t $FRONTEND_IMAGE ."
               }
            }
        }

        stage('Pushing image frontend') {
            steps {
                echo "Push docker image frontend"
                // Utilise withCredentials pour récupérer les credentials Docker Hub
                withCredentials([usernamePassword(credentialsId: '8b6e20fb-38d6-41ce-a2f5-7a32a513881c',
                                                  usernameVariable: 'DOCKER_USERNAME',
                                                  passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "docker login -u \$DOCKER_USERNAME -p \$DOCKER_PASSWORD" // \$ permet de récupérer la valeur de la variable non lu par Jenkins mais par le shell
                    sh "docker push $FRONTEND_IMAGE"  // "$" va permettre à Jenkins de récupérer la valeur de la variable FRONTEND_IMAGE

                }
            }
        } */

       /*  stage('Start Docker Composer frontend') {
            steps {
                echo "starting docker composer"
                // Nettoyage préalable (optionnel)
                sh 'docker compose down --remove-orphans'

                 //lance le conteneur en arriere plan pour permettre à jenkins
                de continuer la prochaine etape du pipeline sans attendrent que
                 ce service docker se termine et reconstruis les images déjà existantes
                 lorsqu'on a eu à effectuer des modifs dans le code source ou dans dockerfile *//*
                sh 'docker compose up -d --build'

                // (Optionnel) Afficher les logs en cas de souci
                sh 'docker compose logs --tail=100'
            }
        } */
    }
}
