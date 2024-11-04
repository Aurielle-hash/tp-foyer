pipeline {
    agent any
    stages{
            stage('GIT'){
                steps {
                    echo 'Pulling... '
                    checkout scm
                }
            }



            stage('MVN BUILD'){
                steps {
                    echo "Clean avec maven"
                    sh "mvn clean"

                    echo "Compilation avec maven"
                    sh "mvn compile"
                }
            }


            stage('MOCKITO'){
                steps {
                    echo "Test unitaire avec mockito"
                    sh "mvn test"
                }
            }

            stage('SONARQUBE'){
                steps {
                    echo "Analyse avec sonarqube"
                    withCredentials ([string(credentialsId: 'f9c1f8ba-2300-4e67-9490-84171cf1fe4e',
                                             variable: 'SONAR_TOKEN')]) {
                        sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=\$SONAR_TOKEN"
                        }
                    //sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=admin -Dsonar.password=giov@nniJB04"
                }
            }


            /*stage('NEXUS DEPLOY'){
                steps {
                    echo "Déploiement sur Nexus"
                    sh "mvn deploy -DskipTests"
                }
            }


            stage('Building image'){
                steps {
                    echo "creating docker image"
                    sh "docker build -t giovannibkn/tp-foyer:5.0.0 ."
                }
            }

            stage('DEPLOY image'){
                steps {
                    echo "push docker image"
                    sh "docker login -u giovannibkn -p giov@nniJB.04"
                    sh "docker push giovannibkn/tp-foyer:5.0.0"
                }
            }

            /*stage('Pushing image'){
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

            stage('Start Docker Composer'){
                        steps {
                            echo "starting docker composer"
                            sh "docker compose down" //arrete le conteneur s'il est deja en cours d'execution
                            sh "docker compose up -d --build"
                        }
            }*/

    }


}