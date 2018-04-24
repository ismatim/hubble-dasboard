#!groovy

pipeline {
    agent any
        tools {
            maven 'maven-3.5.0'
            jdk 'JDK8.144'
        }
    environment {
        HUBBLE_SERVER="http://10.10.20.175"
        devBranch = "dev"
        HUBBLE_TASKRUNNER="hubble.backend.trasksrunner-${BRANCH_NAME}"
        HUBBLE_TASKRUNNER_NAME="hubble/hubble.backend.trasksrunner-${BRANCH_NAME}"
        HUBBLE_BACKEND_API="hubble.backend.api-${BRANCH_NAME}"
        HUBBLE_BACKEND_API_NAME="hubble/hubble.backend.api-${BRANCH_NAME}"
        HUBBLE_MONGODB="mongodb-${BRANCH_NAME}"
        HUBBLE_MONGODB_VOL="/data/mongodb-${BRANCH_NAME}:/data/db/"
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
                              message: "El build ${currentBuild.fullDisplayName} de la rama: $env.BRANCH_NAME ha fallado. (<${env.BUILD_URL}|Abrir>)"
                }
            }
        }
        stage('Deploy'){
            steps{
                script{
                    env.HUBBLE_BACKEND_API_PORT=8080
                    echo "${BRANCH_NAME}==${devBranch}"
                    if (BRANCH_NAME==devBranch){
                        env.HUBBLE_BACKEND_API_PORT="80:8080"
                    }
                }
                sh  '''
                    . ../infrastructure/deploy_hubble_multiple_branches.sh
                    '''
            }
            post {
                success {
                    slackSend channel: '#jenkins', color: 'good',
                              message: "El despliegue ${currentBuild.fullDisplayName} ha sido exitoso."
                                  script {
                                      HUBBLE_BACKEND_API_PORT=sh (
                                              script: 'docker port hubble.backend.api-"${BRANCH_NAME}"',
                                              returnStdout: true).trim().reverse()
                                          echo "HUBBLE APP PORT: ${HUBBLE_BACKEND_API_PORT}"

                                          HUBBLE_BACKEND_API_PORT=HUBBLE_BACKEND_API_PORT.substring(0, HUBBLE_BACKEND_API_PORT.lastIndexOf(":")).reverse()

                                          echo "HUBBLE APP PORT: ${HUBBLE_BACKEND_API_PORT}"

                                          sleep 30
                                          slackSend channel: '#jenkins', color: 'good',
                                      message: "El despliegue ${currentBuild.fullDisplayName} ha sido publicado en: ${HUBBLE_SERVER}:${HUBBLE_BACKEND_API_PORT} - (<${env.BUILD_URL}|Abrir>)"
                                  }
                }
                failure {
                    slackSend channel: '#jenkins', color: '#bf1e1e',
                              message: "El despliegue ${currentBuild.fullDisplayName} de la rama: $env.branch_name ha fallado. (<${env.BUILD_URL}|Abrir>)"
                }
            }
        }
    }
}


