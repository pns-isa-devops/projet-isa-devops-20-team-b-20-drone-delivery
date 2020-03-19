#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        disableConcurrentBuilds()
        timeout(time: 1, unit: "HOURS")
        skipStagesAfterUnstable()
    }
    environment {
        MVN_SETTING_PROVIDER = "3ec57b41-efe6-4628-a6c7-8be5f1c26d77"
    }
    stages {
        stage("Checkout") {
            steps {
                echo "Checkout"
            }
        }
        stage("Analysis on modules 1/4") {
            parallel  {
                stage("statistics-component") {
                    environment {
                        DIR = "./statistics-component/"
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir(DIR) {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir(DIR) {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir(DIR) {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir(DIR) {
                                        sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
                                    }
                                }
                            }
                        }
                        stage("Quality Gate") {
                            steps {
                                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                                    timeout(time: 1, unit: "HOURS") {
                                        waitForQualityGate true
                                    }
                                }
                            }
                        }
                        stage("Artifactory Snapshot") {
                            steps {
                                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                                    dir(DIR) {
                                        sh "mvn -s $MAVEN_SETTINGS deploy"
                                    }
                                }
                            }
                        }
                    }
                    post {
                        success{
                            echo "====++++statistics-component successful++++===="
                        }
                        failure{
                            echo "====++++statistics-component failed++++===="
                        }
                    }
                }
                stage("delivery-component") {
                    environment {
                        DIR = "./delivery-component/"
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir(DIR) {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir(DIR) {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir(DIR) {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir(DIR) {
                                        sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
                                    }
                                }
                            }
                        }
                        stage("Quality Gate") {
                            steps {
                                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                                    timeout(time: 1, unit: "HOURS") {
                                        waitForQualityGate true
                                    }
                                }
                            }
                        }
                        stage("Artifactory Snapshot") {
                            steps {
                                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                                    dir(DIR) {
                                        sh "mvn -s $MAVEN_SETTINGS deploy"
                                    }
                                }
                            }
                        }
                    }
                    post {
                        success{
                            echo "====++++delivery-component successful++++===="
                        }
                        failure{
                            echo "====++++delivery-component failed++++===="
                        }
                    }
                }
            }
        }
        stage("Analysis on modules 2/4") {
            parallel  {
                stage("drone-park-component") {
                    environment {
                        DIR = "./drone-park-component/"
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir(DIR) {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir(DIR) {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir(DIR) {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir(DIR) {
                                        sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
                                    }
                                }
                            }
                        }
                        stage("Quality Gate") {
                            steps {
                                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                                    timeout(time: 1, unit: "HOURS") {
                                        waitForQualityGate true
                                    }
                                }
                            }
                        }
                        stage("Artifactory Snapshot") {
                            steps {
                                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                                    dir(DIR) {
                                        sh "mvn -s $MAVEN_SETTINGS deploy"
                                    }
                                }
                            }
                        }
                    }
                    post {
                        success{
                            echo "====++++drone-park-component successful++++===="
                        }
                        failure{
                            echo "====++++drone-park-component failed++++===="
                        }
                    }
                }
                stage("invoice-component") {
                    environment {
                        DIR = "./invoice-component/"
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir(DIR) {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir(DIR) {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir(DIR) {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir(DIR) {
                                        sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
                                    }
                                }
                            }
                        }
                        stage("Quality Gate") {
                            steps {
                                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                                    timeout(time: 1, unit: "HOURS") {
                                        waitForQualityGate true
                                    }
                                }
                            }
                        }
                        stage("Artifactory Snapshot") {
                            steps {
                                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                                    dir(DIR) {
                                        sh "mvn -s $MAVEN_SETTINGS deploy"
                                    }
                                }
                            }
                        }
                    }
                    post {
                        success{
                            echo "====++++web-services successful++++===="
                        }
                        failure{
                            echo "====++++web-services failed++++===="
                        }
                    }
                }
            }
        }
        stage("Analysis on modules 3/4") {
            parallel  {
                stage("web-services") {
                    environment {
                        DIR = "./web-services/"
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir(DIR) {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir(DIR) {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir(DIR) {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir(DIR) {
                                        sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
                                    }
                                }
                            }
                        }
                        stage("Quality Gate") {
                            steps {
                                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                                    timeout(time: 1, unit: "HOURS") {
                                        waitForQualityGate true
                                    }
                                }
                            }
                        }
                        stage("Artifactory Snapshot") {
                            steps {
                                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                                    dir(DIR) {
                                        sh "mvn -s $MAVEN_SETTINGS deploy"
                                    }
                                }
                            }
                        }
                    }
                    post {
                        success{
                            echo "====++++web-services successful++++===="
                        }
                        failure{
                            echo "====++++web-services failed++++===="
                        }
                    }
                }
                stage("schedule-component") {
                    environment {
                        DIR = "./schedule-component/"
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir(DIR) {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir(DIR) {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir(DIR) {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir(DIR) {
                                        sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
                                    }
                                }
                            }
                        }
                        stage("Quality Gate") {
                            steps {
                                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                                    timeout(time: 1, unit: "HOURS") {
                                        waitForQualityGate true
                                    }
                                }
                            }
                        }
                        stage("Artifactory Snapshot") {
                            steps {
                                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                                    dir(DIR) {
                                        sh "mvn -s $MAVEN_SETTINGS deploy"
                                    }
                                }
                            }
                        }
                    }
                    post {
                        success{
                            echo "====++++schedule-component successful++++===="
                        }
                        failure{
                            echo "====++++schedule-component failed++++===="
                        }
                    }
                }
            }
        }
        stage("Analysis on modules 4/4") {
            parallel  {
                stage("warehouse module") {
                    environment {
                        DIR = "./warehouse-component/"
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir(DIR) {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir(DIR) {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir(DIR) {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir(DIR) {
                                        sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
                                    }
                                }
                            }
                        }
                        stage("Quality Gate") {
                            steps {
                                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                                    timeout(time: 1, unit: "HOURS") {
                                        waitForQualityGate true
                                    }
                                }
                            }
                        }
                        stage("Artifactory Snapshot") {
                            steps {
                                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                                    dir(DIR) {
                                        sh "mvn -s $MAVEN_SETTINGS deploy"
                                    }
                                }
                            }
                        }
                    }
                    post {
                        success{
                            echo "====++++warehouse-component successful++++===="
                        }
                        failure{
                            echo "====++++warehouse-component failed++++===="
                        }
                    }
                }
            }
        }
    }
    post {
        success {
            echo "======== pipeline executed successfully ========"
        }
        failure {
            echo "======== pipeline execution failed========"
        }
    }
}
