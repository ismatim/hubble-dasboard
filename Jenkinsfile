#!groovy

pipeline {
    agent any
        tools {
            maven 'maven-3.5.0'
                jdk 'JDK8.144'
        }
    stages {
        stage('Build') {
            steps {
                sh  '''
                        mvn --batch-mode -V -U clean package -Dmaven.test.skip=true -Dsurefire.useFile=false
                    '''
            }
            post {
                success {
                    slackSend channel: '#jenkins',
                              color: 'good',
                              message: "El build ${currentBuild.fullDisplayName} ha sido exitoso."
                }
                failure {
                    slackSend channel: '#jenkins',
                              color: 'fail',
                              message: "El build ${currentBuild.fullDisplayName} de la rama: $env.BRANCH_NAME ha fallado."
                }
            }
        }
        stage('Deploy'){
            steps{
                withEnv(["HUBBLE_TASKRUNNER=hubble.backend.trasksrunner-${BRANCH_NAME}",
                        "HUBBLE_TASKRUNNER_NAME=localhost:5000/hubble/hubble.backend.trasksrunner-${BRANCH_NAME}",
                        "HUBBLE_FRONTEND=hubble.frontend.web-${BRANCH_NAME}",
                        "HUBBLE_FRONTEND_NAME=localhost:5000/hubble/hubble.frontend.web-${BRANCH_NAME}",
                        "HUBBLE_MONGODB=mongodb-${BRANCH_NAME}",
                        "HUBBLE_MONGODB_VOL=/data/mongodb-${BRANCH_NAME}:/data/db/",
                        "HUBBLE_FRONTEND_PORT=8080",
                        "HUBBLE_APP_PORT=0"]){
                    sh '''
                        . ../infrastructure/deploy_hubble_multiple_branches.sh
                        '''
                }
            }
            post {
                success {
                    slackSend channel: '#jenkins', color: 'good',
                              message: "El despliegue ${currentBuild.fullDisplayName} ha sido exitoso."
                    script {
                          HUBBLE_APP_PORT=sh (
                                  script: 'docker port hubble.frontend.web-"${BRANCH_NAME}"',
                                  returnStdout: true).trim().reverse().take(5).reverse()

                          def HUBBLE_SERVER="http://10.10.20.175"
                          echo "HUBBLE APP PORT: ${HUBBLE_APP_PORT}"

                          sleep 20
                          slackSend channel: '#jenkins', color: 'good',
                             message: "El despliegue ${currentBuild.fullDisplayName} ha sido publicado en:${HUBBLE_SERVER}:${HUBBLE_APP_PORT}"
                    }
                }
                failure {
                    slackSend channel: '#jenkins', color: 'fail',
                             message: "El despliegue ${currentBuild.fullDisplayName} de la rama: $env.branch_name ha fallado."
                }
            }
        }
    }
}


