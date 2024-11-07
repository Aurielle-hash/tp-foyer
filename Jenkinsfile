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

           stage('SonarQube Analysis') {
                    steps {
                        withSonarQubeEnv('SonarQubeServer') { // Use the SonarQube instance configured in Jenkins
                            sh '''
                             mvn -X clean verify sonar:sonar \
                                   -Dsonar.projectKey=Test \
                                   -Dsonar.projectName="Test" \
                                   -Dsonar.host.url=http://192.168.50.4:9000 \
                                   -Dsonar.login=sqp_04f4a37925227fbe707b011c181ed0a1a2fd1f2c \
                                   -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                            '''
                        }
                    }
                }



                stage('Mockito') {
                    steps {
                        echo 'Running Mockito tests...'
                        sh 'mvn test'
                    }
                }

                stage('Deploy to Nexus') {
                            steps {
                                withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')])
                                {
                                    sh """
                                        mvn deploy -DskipTests -DaltDeploymentRepository=nexus::default::http://localhost:8081/repository/maven-releases/ \
                                                   -Dnexus.username=$NEXUS_USERNAME -Dnexus.password=$NEXUS_PASSWORD
                                    """
                                }
                            }
                }






    }
}
