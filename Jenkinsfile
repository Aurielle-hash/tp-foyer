pipeline {
    agent any
    stages{
            stage('GIT'){
                steps {
                    echo 'Pulling... '
                    checkout scm
                }
            }




            stage('MVN CLEAN'){
                steps {
                    echo "Clean avec maven"
                    sh "mvn clean"
                }
            }

            stage('MVN COMPILE'){
                steps {
                    echo "Compilation avec maven"
                    sh "mvn compile"
                }
            }

            stage('MVN test'){
                 steps {
                     sh "mvn test"
                 }
            }

            stage('MVN SONARQUBE'){
                steps {
                    echo "Analyse avec sonarqube"
                    sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=admin -Dsonar.password=giov@nniJB04"
                }
            }

            stage('NEXUS Deploy'){
                steps {
                    echo "Déploiement sur Nexus"
                    sh "mvn deploy -DskipTests"
                }
            }

    }


}