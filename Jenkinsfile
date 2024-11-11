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

        /*
        stage('OWASP Dependency-Check') {
            steps {
                echo 'Exécution de l\'analyse de dépendances avec OWASP Dependency-Check'
                sh 'mvn org.owasp:dependency-check-maven:check'
            }
        }

        stage('Publish Dependency-Check Report') {
            steps {
                dir('tp-foyer') {
                    echo 'Publication du rapport OWASP Dependency-Check'
                    publishHTML(
                        target: [
                            reportName: 'OWASP Dependency-Check Report',
                            reportDir: '/', // Le répertoire où le rapport est généré
                            reportFiles: 'index.html', // Le fichier HTML généré par OWASP Dependency-Check
                            keepAll: true,
                            alwaysLinkToLastBuild: false
                        ]
                    )
                }
            }
        }
        */

        stage('Security Scan') {
            steps {
                script {
                     sh 'docker login -u benhammedmaissa -p Meyssouna21!'

                    sh 'docker pull dhouari/devsecops' // normalement hedhi
                    sh 'docker run --rm dhouari/devsecops benhammedmaissa/tpfoyer-devops-5.0.0'
                }
            }
        }

        /*
        stage('MVN Test') {
            steps {
                echo "Test avec maven"
                sh "mvn -X test"
            }
        }
        */

        /*
        stage('Maven SonarQube') {
            steps {
                echo "Sonarqube analysis"
                sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.56.44:9000 -Dsonar.login=admin -Dsonar.password=Meyssouna21!"
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
        */

        /*
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t benhammedmaissa/tpfoyer-devops-5.0.0 .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    sh 'docker login -u benhammedmaissa -p Meyssouna21!'
                    sh 'docker push benhammedmaissa/tpfoyer-devops-5.0.0'
                }
            }
        }
        */
    }
}
