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
                    mvn clean verify sonar:sonar \
                      -Dsonar.projectKey=tp-foyer \
                      -Dsonar.projectName='tp-foyer' \
                      -Dsonar.host.url=http://192.168.50.4:9000 \
                      -Dsonar.token=sqp_d4f372c34d24f505e563ad3793fba27c5fa6d1e3
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
                withCredentials([usernamePassword(credentialsId: 'deploymentRepo', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
                    sh """
                        mvn deploy -X -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8081/repository/maven-releases/ \
                            -Dnexus.username=$NEXUS_USERNAME -Dnexus.password=$NEXUS_PASSWORD
                    """
                }
            }
        }


     stage('Run Ansible Playbook') {
         steps {
             echo 'Running Ansible Playbook on Vagrant...'
             sshagent(['SSHansible']) {
                 sh 'ansible-playbook -i /home/vagrant/synced_folder/inventory /home/vagrant/synced_folder/playbooks.yml -vvv'
             }
         }
     }







        stage('Docker Image') {
            steps {
                echo 'Constructing Docker image...'
                sh 'docker build -t 0123456789rimen/tp-foyer:1.0.0 .'
            }
        }

        stage('Docker-Compose') {
            steps {
                echo 'Starting services with Docker Compose...'
                sh 'docker-compose -f ./docker-compose.yml up -d'
            }
        }

        stage('Docker Hub') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        # Log in to Docker Hub
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin

                        # Tag the image for Docker Hub (with your Docker Hub username prefix)
                        docker tag 0123456789rimen/tp-foyer:1.0.0 $DOCKER_USER/tp-foyer:1.0.0

                        # Push the tagged image to Docker Hub
                        docker push $DOCKER_USER/tp-foyer:1.0.0

                        # Log out from Docker Hub
                        docker logout
                    '''
                }
            }
        }

        stage('Grafana Monitoring Setup') {
            steps {
                echo 'Setting up Grafana monitoring...'
                sh '''
                    # Create prometheus.yml file if it doesn't exist
                    cat <<EOL > $WORKSPACE/prometheus.yml
                    global:
                      scrape_interval: 15s
                      evaluation_interval: 15s

                    scrape_configs:
                      - job_name: 'jenkins'
                        static_configs:
                          - targets: ['192.168.50.4:8080'] # Replace with Jenkins IP and Port
                      - job_name: 'grafana'
                        static_configs:
                          - targets: ['localhost:3000']

                    EOL

                    # Start existing Prometheus container if it's not already running
                    if [ $(docker ps -aq -f name=prometheus -f status=exited) ]; then
                        docker start prometheus
                    elif [ -z $(docker ps -aq -f name=prometheus) ]; then
                        docker run -d --name prometheus -p 9090:9090 \
                            -v $WORKSPACE/prometheus.yml:/etc/prometheus/prometheus.yml \
                            prom/prometheus
                    fi

                    # Start existing Grafana container if it's not already running
                    if [ $(docker ps -aq -f name=grafana -f status=exited) ]; then
                        docker start grafana
                    elif [ -z $(docker ps -aq -f name=grafana) ]; then
                        docker run -d --name grafana -p 3000:3000 grafana/grafana
                    fi
                '''
                echo 'Grafana and Prometheus are set up and running.'
            }
        }
    }
}
