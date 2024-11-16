pipeline {
    agent any

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

      stage('Deploy with Ansible') {
            steps {
                script {
                    // Execute the Ansible playbook
                    sh 'ansible-playbook -i $ANSIBLE_HOSTS deploy.yml'
                }
            }
        }

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

           /* stage('Docker Compose Up') {
                    steps {
                        script {
                            sh 'docker-compose -f docker-compose.yml up -d --build'
                        }
                    }
                }*/

}

}
