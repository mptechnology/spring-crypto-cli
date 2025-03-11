#!/usr/bin/env groovy
@Library("mp-jenkins-pipeline-shared") _

pipeline {
    agent { label("ignite || hodor") }

    parameters {
        booleanParam(name: "do_release", defaultValue: false, description: "Do you want to release the application?")
        string(name: "release_version", defaultValue: "", description: "Which version number should be used?")
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: "15"))
        timeout(time: 60, unit: "MINUTES")
        timestamps()
    }

    tools {
        jdk "OpenJDK-21.0.6+7"
    }

    environment {
        MAVEN_OPTS = "-Xmx1g"
    }

    stages {
        stage("Info") {
            steps {
                sh "java -version"

                sh "./mvnw -version"
                echo "MAVEN_OPTS: ${env.MAVEN_OPTS}"

                echo "Node Name: ${env.NODE_NAME}"
                echo "Executor Number: ${env.EXECUTOR_NUMBER}"

                echo "Source Branch (env.BRANCH_NAME): ${env.BRANCH_NAME}"
                echo "PR Source Branch (env.CHANGE_BRANCH): ${env.CHANGE_BRANCH}"
                echo "PR ID (env.CHANGE_ID): ${env.CHANGE_ID}"
                echo "PR Target (env.CHANGE_TARGET): ${env.CHANGE_TARGET}"

                echo "Release: ${params.do_release} with version: ${params.release_version}"

                sendReleaseDeployNotifications(params.do_release, "STARTED", "#mp-builds", "", env.BRANCH_NAME, params.do_release, params.release_version)
            }
        }

        stage("Set Build Name") {
            steps {
                script {
                    if (env.CHANGE_ID) {
                        currentBuild.displayName = "#${env.BUILD_NUMBER} - ${env.CHANGE_BRANCH}"
                    } else {
                        currentBuild.displayName = "#${env.BUILD_NUMBER} - ${env.BRANCH_NAME}"
                    }
                }
            }
        }

        stage("Build") {
            steps {
                script {
                    if (params.do_release) {
                        sh "./mvnw clean deploy scm:tag -Drevision=${params.release_version}"
                    } else {
                        sh "./mvnw clean verify"
                    }
                }
                archiveArtifacts artifacts: "**/target/*.jar", fingerprint: true
            }
            post {
                always {
                    junit testResults: "**/target/surefire-reports/TEST-*.xml", allowEmptyResults: true
                }
            }
        }
    }

    post {
        always {
            sendReleaseDeployNotifications(params.do_release, currentBuild.result, "#mp-builds", "", env.BRANCH_NAME, params.do_release, params.release_version)
        }

        unstable {
            sendNotifications(!(params.do_release), currentBuild.result, "#mp-builds")
        }

        failure {
            sendNotifications(!(params.do_release), currentBuild.result, "#mp-builds")
        }
    }
}
