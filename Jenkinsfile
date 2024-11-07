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
                        withSonarQubeEnv('SonarQube') { // Use the SonarQube instance configured in Jenkins
                            sh '''
                              mvn sonar:sonar \
                              -Dsonar.projectKey=Test \
                              -Dsonar.projectName="Test" \
                              -Dsonar.host.url=http://192.168.50.4:9000 \
                              -Dsonar.token=sqp_04f4a37925227fbe707b011c181ed0a1a2fd1f2c \
                              -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                            '''
                        }
                    }
                }




    }
}
