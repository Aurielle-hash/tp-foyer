pipeline {
   agent { label 'build_slaves2' }


    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling... '
                git branch: 'etudient',
                                    url: 'https://github.com/Aurielle-hash/tp-foyer.git',
                                  // credentialsId: 'e86bd8f8-4943-4566-bc89-ae54b0151dac'
            }
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

/*
        stage('MVN Test') {
            steps {
               echo "Test avec maven"
                sh "mvn test"
            }
        }
  */
       /* stage('Maven SonarQube'){
        steps {
        	echo "Sonarqube analysis"
        	                sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.56.44:9000 -Dsonar.login=admin -Dsonar.password=Meyssouna21!"
        	                
        	                

}


        }*/
        
        

        //stage('NEXUS'){
        //steps{
        //echo "nexus deploiment"
        //sh "mvn deploy -DskipTests"
        //}
        //}


        
        
    }

}


