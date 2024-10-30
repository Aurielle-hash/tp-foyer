pipeline {
    agent any
    stages{
            stage('GIT'){
                steps {
                    echo 'Pulling... '
                    checkout scm
                }
            }



            stage('MVN'){
                steps {
                    echo "Clean avec maven"
                    sh "mvn clean"

                    echo "Compilation avec maven"
                    sh "mvn compile"
                }
            }

            /*stage('MVN COMPILE'){
                steps {

                }
            }*/


            stage('MVN SONARQUBE'){
                steps {
                    echo "Analyse avec sonarqube"
                    sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.50.4:9000 -Dsonar.login=admin -Dsonar.password=giov@nniJB04"
                }
            }

            stage('NEXUS DEPLOY'){
                steps {
                    echo "Déploiement sur Nexus"
                    sh "mvn deploy -DskipTests"
                }
            }

    }


}