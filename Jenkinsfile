pipeline {
    agent any

    tools {
        maven 'Maven3'        // Configure in Jenkins: Manage Jenkins > Global Tool Configuration
        jdk 'JDK17'           // Configure JDK 17 in Jenkins
    }



    environment {
//         SONAR_HOST_URL = credentials('SONAR_HOST_URL')
//         SONAR_TOKEN    = credentials('SONAR_TOKEN')
//
//         REMOTE_HOST = credentials('REMOTE_HOST')
//         REMOTE_PORT = credentials('REMOTE_PORT')
//         REMOTE_USER = credentials('REMOTE_USER')
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

//         stage('SonarQube Analysis') {
//             steps {
//                 withSonarQubeEnv('SonarQube') {
//                     sh """
//                     mvn sonar:sonar \
//                     -Dsonar.host.url=$SONAR_HOST_URL \
//                     -Dsonar.login=$SONAR_TOKEN
//                     """
//                 }
//             }
//         }
//
//         stage('Archive WAR') {
//             steps {
//                 archiveArtifacts artifacts: 'target/project-one-0.0.1-SNAPSHOT.war', fingerprint: false
//             }
//         }
//
//         stage('Deploy to Tomcat') {
//             steps {
//                 sshagent(credentials: ['SSH_PRIVATE_KEY']) {
//                     sh """
//                     scp -P ${REMOTE_PORT} \
//                     -o StrictHostKeyChecking=no \
//                     target/project-one-0.0.1-SNAPSHOT.war \
//                     ${REMOTE_USER}@${REMOTE_HOST}:/tmp/
//
//                     ssh -p ${REMOTE_PORT} \
//                     -o StrictHostKeyChecking=no \
//                     ${REMOTE_USER}@${REMOTE_HOST} << EOF
//                     sudo mv /tmp/project-one-0.0.1-SNAPSHOT.war /var/lib/tomcat11/webapps/project-one.war
//                     sudo chown tomcat:tomcat /var/lib/tomcat11/webapps/project-one.war
//                     sudo systemctl restart tomcat11
//                     EOF
//                     """
//                 }
//             }
//         }
    }

    post {
        success {
            echo 'Application built, tested, analyzed, and deployed successfully!'
        }

        failure {
            echo 'Pipeline failed.'
        }

        always {
                script {
                    if (env.WORKSPACE) {
                        cleanWs()
                    }
                }
            }
    }
}