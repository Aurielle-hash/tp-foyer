pipeline {
    agent any
    stages{
            stage('GIT'){
                steps {
                    echo 'Pulling... '
                    checkout scm
                }
            }



            stage('MVN Clean'){
                steps {
                    echo "Clean avec maven"
                    sh "mvn clean package"
                }
            }
            stage('MVN Complie'){
                    steps {
                            echo "Compilation avec maven"
                            sh "mvn compile"
                    }
                }

            stage('Dependency Analysis with OWASP Dependency-Check') {
                steps {
                        sh 'mvn org.owasp:dependency-check-maven:check'
                }
            }

            stage('Publish Dependency-Check Report') {
                        steps {
                            echo 'Publication du rapport OWASP Dependency-Check'
                            publishHTML(
                                target: [
                                    reportName: 'OWASP Dependency-Check Report',
                                    reportDir: 'target/dependency-check-report', // Le répertoire où le rapport est généré
                                    reportFiles: 'index.html', // Le fichier HTML généré par OWASP Dependency-Check
                                    keepAll: true
                                ]
                            )
                        }
            }


            stage('MOCKITO'){
                steps {
                    echo "Test unitaire avec mockito"
                    sh "mvn test"
                }
            }

            stage('Docker Security Scanning with Trivy') {
                        steps {
                                script {
                                        // Récupérer dynamiquement les IDs des conteneurs en cours d'exécution
                                        def containerIds = sh(script: "docker ps -q", returnStdout: true).trim().split("\n")
                                        def reportsDir = "trivy-reports/"
                                        sh "mkdir -p ${reportsDir}"

                                        // Exécuter un scan Trivy pour chaque conteneur en parallèle
                                        parallel containerIds.collectEntries { containerId ->
                                            ["Scan ${containerId}": {
                                                try {
                                                    // Récupérer l'image associée à l'ID du conteneur
                                                    def image = sh(script: "docker inspect --format '{{.Config.Image}}' ${containerId}", returnStdout: true).trim()
                                                    echo "Scanning container image: ${image} with Trivy"

                                                    // Exécuter le scan Trivy et sauvegarder le rapport en format JSON
                                                    sh """
                                                    trivy image \
                                                    --severity HIGH,CRITICAL \
                                                    --exit-code 1 \
                                                    --no-progress \
                                                    --format json \
                                                    --output ${reportsDir}/trivy-report-${containerId}.json \
                                                    ${image}
                                                    """

                                                } catch (Exception e) {
                                                    echo "Failed to scan container ${containerId}: ${e}"
                                                }
                                            }]
                                        }

                                }
                        }
                    }

                    stage('Conversion JSON en HTML') {
                        steps {
                            script {
                                // S'assurer que Python et les dépendances sont installés
                                sh '''
                                pip3 install --user json2html

                                mkdir -p rapports_html
                                for file in trivy-reports/*.jso; do
                                    if [ -f "$file" ]; then
                                        base_name=$(basename "$file" .json)
                                        python3 trivy_to_html.py "$file" "trivy-reports/${base_name}.html"
                                    fi
                                done
                                '''
                            }
                        }
                    }


                        // Étape pour archiver les rapports de Trivy
                        stage('Archive Reports') {
                                     steps {
                                             archiveArtifacts artifacts: "trivy-reports/*.html", allowEmptyArchive: true
                                     }
                        }




            stage('SONARQUBE'){
                steps {
                    echo "Analyse avec sonarqube"
                    withCredentials ([string(credentialsId: 'f9c1f8ba-2300-4e67-9490-84171cf1fe4e',
                                             variable: 'SONAR_TOKEN')]) {
                        sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=\$SONAR_TOKEN"
                        }
                }
            }




            stage('Nexus Deploy') {
            steps {
                echo "Déploiement sur Nexus"
                    withCredentials([usernamePassword(credentialsId: 'cfaa007a-d388-464e-b650-7b06bea55321',
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

            stage('Building image'){
                steps {
                    echo "creating docker image"
                    sh "docker build -t $BACKEND_IMAGE ."
                }
            }




            stage('Pushing image'){
                steps {
                    echo "pushing docker image"
                    // Utilise withCredentials pour récupérer les credentials Docker Hub
                    withCredentials([usernamePassword(credentialsId: 'a23c34c5-6e74-4665-b9ed-d3ceacf70c04',
                                          usernameVariable: 'DOCKER_USERNAME',
                                          passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "docker login -u \$DOCKER_USERNAME -p \$DOCKER_PASSWORD" // \$ permet de récupérer la valeur de la variable non lu par Jenkins mais par le shell
                    sh "docker push $BACKEND_IMAGE"  // "$" va permettre à Jenkins de récupérer la valeur de la variable Tpfoyer_Image
                    }
                }
            }


            stage('Start Docker Composer'){
                        steps {
                            echo 'starting docker composer'
                            sh 'docker compose down' //arrete le conteneur s'il est deja en cours d'execution
                            sh 'docker compose up -d'
                        }
            }

/*
            stage('building frontend image') {
                        steps {
                            echo "creating frontend docker image"
                            dir('tp-foyer-frontend') {
                            //  build avec cache pour eviter de retelecharger les dependances

                                sh "docker build --cache-from=$FRONTEND_IMAGE -f Dockerfile-angular -t $FRONTEND_IMAGE ."
                           }
                        }
            }
*/

    }


}