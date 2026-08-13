// pipeline {
//     agent any
//
//     tools {
//         maven 'Maven3'        // Configure in Jenkins: Manage Jenkins > Global Tool Configuration
//         jdk 'JDK17'           // Configure JDK 17 in Jenkins
//     }
//
//
//
// //     environment {
// //         SONAR_HOST_URL = credentials('SONAR_HOST_URL')
// //         SONAR_TOKEN    = credentials('SONAR_TOKEN')
// //
// //         REMOTE_HOST = credentials('REMOTE_HOST')
// //         REMOTE_PORT = credentials('REMOTE_PORT')
// //         REMOTE_USER = credentials('REMOTE_USER')
// //    }
//
//     stages {
//
//         stage('Checkout') {
//             steps {
//                 checkout scm
//             }
//         }
//
//         stage('Build') {
//             steps {
//                 sh 'mvn clean package'
//             }
//         }
//
//         stage('Run Tests') {
//             steps {
//                 sh 'mvn test'
//             }
//         }
//
// //         stage('SonarQube Analysis') {
// //             steps {
// //                 withSonarQubeEnv('SonarQube') {
// //                     sh """
// //                     mvn sonar:sonar \
// //                     -Dsonar.host.url=$SONAR_HOST_URL \
// //                     -Dsonar.login=$SONAR_TOKEN
// //                     """
// //                 }
// //             }
// //         }
// //
// //         stage('Archive WAR') {
// //             steps {
// //                 archiveArtifacts artifacts: 'target/project-one-0.0.1-SNAPSHOT.war', fingerprint: false
// //             }
// //         }
// //
// //         stage('Deploy to Tomcat') {
// //             steps {
// //                 sshagent(credentials: ['SSH_PRIVATE_KEY']) {
// //                     sh """
// //                     scp -P ${REMOTE_PORT} \
// //                     -o StrictHostKeyChecking=no \
// //                     target/project-one-0.0.1-SNAPSHOT.war \
// //                     ${REMOTE_USER}@${REMOTE_HOST}:/tmp/
// //
// //                     ssh -p ${REMOTE_PORT} \
// //                     -o StrictHostKeyChecking=no \
// //                     ${REMOTE_USER}@${REMOTE_HOST} << EOF
// //                     sudo mv /tmp/project-one-0.0.1-SNAPSHOT.war /var/lib/tomcat11/webapps/project-one.war
// //                     sudo chown tomcat:tomcat /var/lib/tomcat11/webapps/project-one.war
// //                     sudo systemctl restart tomcat11
// //                     EOF
// //                     """
// //                 }
// //             }
// //         }
//     }
//
//     post {
//         success {
//             echo 'Application built, tested, analyzed, and deployed successfully!'
//         }
//
//         failure {
//             echo 'Pipeline failed.'
//         }
//
//         always {
//                 script {
//                     if (env.WORKSPACE) {
//                         cleanWs()
//                     }
//                 }
//             }
//     }
// }

pipeline {

     agent any

        tools {
            maven 'Maven3'        // Configure in Jenkins: Manage Jenkins > Global Tool Configuration
             jdk 'JDK17'           // Configure JDK 17 in Jenkins
        }

    environment {
        REMOTE_HOST = "182.252.68.169"
        REMOTE_PORT = "2222"
        REMOTE_USER = "mist"

        SONAR_HOST_URL = "http://10.104.2.130:9000"
        SONAR_PROJECT_KEY = "project-two"
        SONAR_PROJECT_NAME = "project-two"
        //SONAR_TOKEN = "sqp_66f5326412c794e08a14813239edce9701f9f193";//"sqp_f398602d75acc3611634dc7c88db1e2b450cad0d"

//         DOCKER_PASSWORD = "P@%%w0rd#"
//         DOCKER_USERNAME = "shatil06"
        DOCKER_IMAGE = "shatil06/project-two"
        DOCKER_TAG = "latest"
    }
     stages {
            stage('Check Environment') {
                steps {
                    sh '''
                        echo "Java:"
                        java -version

                        echo "Maven:"
                        mvn -version

                        echo "Docker:"
                        docker --version
                    '''
                }
            }

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

            stage('SonarQube Analysis') {
                steps {
                    withCredentials([
                        string(
                            credentialsId: 'sonarqube-token',
                            variable: 'SONAR_TOKEN'
                        )
                    ]) {
                        sh '''
                            mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                                -Dsonar.projectKey="$SONAR_PROJECT_KEY" \
                                -Dsonar.projectName="$SONAR_PROJECT_NAME" \
                                -Dsonar.host.url="$SONAR_HOST_URL" \
                                -Dsonar.token="$SONAR_TOKEN"
                        '''
                    }
                }
            }

            stage('Run Tests') {
                steps {
                    sh 'mvn test'
                }
            }

            stage('Docker Login') {
                steps {
                    withCredentials([
                        usernamePassword(
                            credentialsId: 'dockerhub-creds',
                            usernameVariable: 'DOCKER_USERNAME',
                            passwordVariable: 'DOCKER_PASSWORD'
                        )
                    ]) {
                        sh '''
                            echo "$DOCKER_PASSWORD" | docker login \
                                --username "$DOCKER_USERNAME" \
                                --password-stdin
                        '''
                    }
                }
            }

            stage('Docker Build') {
                steps {
                    sh '''
                        docker build \
                            -t "$DOCKER_IMAGE:$DOCKER_TAG" \
                            .
                    '''
                }
            }

            stage('Docker Push') {
                steps {
                    sh '''
                        docker push "$DOCKER_IMAGE:$DOCKER_TAG"
                    '''
                }
            }
        }

        post {
            success {
                echo 'Pipeline completed successfully!'
            }

            failure {
                echo 'Pipeline failed.'
            }

            always {
                cleanWs()
            }
        }

//     stages {
//
//         stage('Checkout') {
//             steps {
//                 checkout scm
//             }
//         }
//
//         stage('Test Jenkins Environment') {
//             steps {
//                 sh '''
//                     echo "Jenkins pipeline is working!"
//
//                     echo "Current directory:"
//                     pwd
//
//                     echo "Files:"
//                     ls -la
//
//                     echo "Java version:"
//                     java -version || true
//
//                     echo "Maven version:"
//                     mvn -version || true
//                 '''
//             }
//         }
//
//         stage('Build') {
//             steps {
//                 sh 'mvn clean package'
//             }
//         }
//
//         stage('SonarQube Analysis') {
//             steps {
//                 sh '''
//                     mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
//                       -Dsonar.projectKey=$SONAR_PROJECT_KEY \
//                       -Dsonar.projectName=$SONAR_PROJECT_NAME \
//                       -Dsonar.host.url=$SONAR_HOST_URL \
//                       -Dsonar.token=$SONAR_TOKEN
//                 '''
//             }
//         }
//
//
//
//         stage('Run Tests') {
//             steps {
//                 sh 'mvn test'
//             }
//         }
//
//
//            stage('Docker Login') {
//                     steps {
//
//                         withCredentials([
//                             usernamePassword(
//                                 credentialsId: 'dockerhub-creds',
//                                 usernameVariable: 'DOCKER_USERNAME',
//                                 passwordVariable: 'DOCKER_PASSWORD'
//                             )
//                         ]) {
//
//                             sh '''
//                                 echo $DOCKER_PASSWORD | docker login \
//                                 -u $DOCKER_USERNAME \
//                                 --password-stdin
//                             '''
//                         }
//                     }
//                 }
//
//
//
//                 stage('Docker Push') {
//                     steps {
//                         sh '''
//                             docker push $DOCKER_IMAGE:$DOCKER_TAG
//                         '''
//                     }
//                 }
//
//
//     }
//
//     post {
//         success {
//             echo 'Application build and SonarQube scan completed successfully!'
//         }
//
//         failure {
//             echo 'Pipeline failed.'
//         }
//
//         always {
//             cleanWs()
//         }
//     }
}