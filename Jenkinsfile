#!/usr/bin/env groovy

pipeline{
    agent any
    options {
        disableConcurrentBuilds()
        timeout(time: 1, unit: "HOURS")
    }
    stages{
        stage("Checkout"){
            steps{
                echo "Checkout"
            }
        }
        stage("Compile modules"){
            steps{
                echo "clean compile each modules"
                sh "mvn clean compile"
            }
        }
        stage("Unit Tests modules"){
            steps{
                echo "unit tests each modules"
                sh "mvn test"
            }
        }
        stage("Test Mutation modules"){
            steps{
                echo "Mutation with piTest"
                sh "mvn install org.pitest:pitest-maven:mutationCoverage"
            }
        }
        stage("Sonar Analysis"){
            steps {
                echo 'Sonar Analysis'
                withSonarQubeEnv('Sonarqube_env') {
                    sh 'mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport'
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate true
                }
                echo 'passed'
            }
        }
    }
    post{
        always{
            archiveArtifacts artifacts: "target/**/*", fingerprint: true
            junit "target/surefire-reports/*.xml"
            echo "======== pipeline report ========"
        }
        success{
            echo "======== pipeline executed successfully ========"
        }
        failure{
            echo "======== pipeline execution failed========"
        }
    }
}
