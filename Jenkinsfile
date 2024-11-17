pipeline {
    agent any
   environment {
        APP_HOST = '127.0.0.1'
    }
    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling.....'
                checkout([$class: 'GitSCM', branches: [[name: 'etudient']], userRemoteConfigs: [[url: 'https://github.com/Aurielle-hash/tp-foyer.git', credentialsId: 'f8a1ea3e-fc24-4c47-adcf-019ea3f894e7']]])
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

        stage('Maven Build') {
            steps {
                echo "Building avec maven"
                sh "mvn clean package"
            }
        }



     /*stage('Deploy with Ansible') {
         steps {
             script {
                 // Print the environment variable for debugging
                 sh 'echo "Inventory: $ANSIBLE_HOSTS"'
                 sh 'ansible-playbook -i $ANSIBLE_HOSTS deploy.yml'
             }
         }
     }
*/
      /*stage('Dependency Check') {
          steps {
              script {
                  // Ensure output directory exists
                  sh 'mkdir -p reports/dependency-check'

                  // Run Dependency-Check Analysis
                  dependencyCheck additionalArguments: '''--scan .
                      --format ALL
                      --project "tp-foyer"
                      --out reports/dependency-check''',
                      odcInstallation: 'Default'
              }
          }
      }


               stage('Publish Report') {
                   steps {
                       dependencyCheckPublisher pattern: 'reports/dependency-check/dependency-check-report.html'
                   }
               }
               */





        /*
  stage('NEXUS') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'fcc467b6-97da-40aa-a0b6-4dd3f9b24c09', usernameVariable: 'admin', passwordVariable: 'Meyssouna21!')]) {
                    echo "Nexus deployment"
                    sh "mvn deploy -DskipTests"
                }
            }
        }




       stage('MVN Test') {
            steps {
                echo "Test avec maven"
                sh "mvn -X test "
               // mvn -X test -Dtest=EtudiantserviceImplTestMock
            }
        }

           stage('Maven SonarQube') {
                  steps {
                   echo "Sonarqube analysis"
              // sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.56.44:9000 -Dsonar.login=admin -Dsonar.password=Meyssouna21!"
                 sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.56.44:9000 -Dsonar.login=admin -Dsonar.password=Meyssouna21!"
                  }
              }

            */





        /*stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t benhammedmaissa/tpfoyer-devops-5.0.0 .'
                }
            }
        } */

                   /* stage('Push Docker Image') {
            steps {
                script {
                    sh 'docker login -u benhammedmaissa -p Meyssouna21!'
                    sh 'docker push benhammedmaissa/tpfoyer-devops-5.0.0'
                                 }
            }
        }*/

           // stage('Docker Compose Up') {
             //       steps {
               //         script {
                 //           sh 'docker-compose -f docker-compose.yml up -d --build'
                   //     }
                    //}
                //}

               stage('Setting up OWASP ZAP docker container') {
                      steps {
                           script {
                                       echo "Pulling up last OWASP ZAP container --> Start"
                                      sh 'docker login -u benhammedmaissa -p Meyssouna21!'
                                        sh 'docker pull rtencatexebia/owasp-zap'
                                       echo "Pulling up last VMS container --> End"
                                        echo "Starting container --> Start"
                                       sh """
                                        docker run -dt --name owasp \
                                        rtencatexebia/owasp-zap \
                                        /bin/bash
                                        """
                                }
                            }
                        }


            stage('Running OWASP ZAP Scan') {
                steps {
                    script {
                        echo "Running OWASP ZAP scan --> Start"

                        // Define target URL for your application
                        def targetUrl = 'http://127.0.0.1'
                      def reportDir = "${env.WORKSPACE}/zap-reports"
                     sh "mkdir -p ${reportDir}"
                        // Start the ZAP proxy in daemon mode
                         sh """
                                      docker exec -d -v ${reportDir}:/zap/reports owasp zap-baseline.py -t $targetUrl -r /zap/reports/zap-report.xml
                                  """

                        // Wait for the scan to finish (You can adjust the timeout based on the app size)
                        echo "Waiting for ZAP to complete scan --> Start"
                        sleep 10 // Adjust as necessary based on the application size and complexity
                        echo "Waiting for ZAP to complete scan --> End"
                    }
                }
            }

          stage('Retrieving Scan Report') {
              steps {
                  script {
                      echo "Retrieving OWASP ZAP scan report --> Start"
                      // Ensure the report has been generated and retrieve it
                         echo "Report available at: ${env.WORKSPACE}/zap-reports/zap-report.xml"
                                  echo "Retrieving OWASP ZAP scan report --> End"

                   // sh """
                     //   docker exec owasp ls /zap/reports/zap-report.xml
                    //"""

                     //sh 'docker cp owasp:/zap/reports/zap-report.xml ./zap-report.xml'
                      //echo "Retrieving OWASP ZAP scan report --> End"
                  }
              }
          }

            stage('Post-scan Actions') {
                steps {
                    script {
                        echo "Post-scan actions --> Start"
                        // You can parse the report or send it as an email or upload it somewhere for further processing
                        // Example: Publish the ZAP report as an artifact
                        archiveArtifacts artifacts: 'zap-report.xml', allowEmptyArchive: true
                        echo "Post-scan actions --> End"
                    }
                }
            }













}

}
