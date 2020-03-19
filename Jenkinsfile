#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        disableConcurrentBuilds()
        timeout(time: 1, unit: "HOURS")
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
                                dir($DIR) {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir($DIR) {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir($DIR) {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir($DIR) {
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
                                configFileProvider([configFile(fileId: "3ec57b41-efe6-4628-a6c7-8be5f1c26d77", variable: "MAVEN_SETTINGS")]) {
                                    dir($DIR) {
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
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir("./delivery-component/") {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir("./delivery-component/") {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir("./delivery-component/") {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir("./delivery-component/") {
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
                                configFileProvider([configFile(fileId: "3ec57b41-efe6-4628-a6c7-8be5f1c26d77", variable: "MAVEN_SETTINGS")]) {
                                    dir("./delivery-component/") {
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
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir("./drone-park-component/") {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir("./drone-park-component/") {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir("./drone-park-component/") {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir("./drone-park-component/") {
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
                                configFileProvider([configFile(fileId: "3ec57b41-efe6-4628-a6c7-8be5f1c26d77", variable: "MAVEN_SETTINGS")]) {
                                    dir("./drone-park-component/") {
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
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir("./invoice-component/") {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir("./invoice-component/") {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir("./invoice-component/") {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir("./invoice-component/") {
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
                                configFileProvider([configFile(fileId: "3ec57b41-efe6-4628-a6c7-8be5f1c26d77", variable: "MAVEN_SETTINGS")]) {
                                    dir("./invoice-component/") {
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
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir("./web-services/") {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir("./web-services/") {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir("./web-services/") {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir("./web-services/") {
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
                                configFileProvider([configFile(fileId: "3ec57b41-efe6-4628-a6c7-8be5f1c26d77", variable: "MAVEN_SETTINGS")]) {
                                    dir("./web-services/") {
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
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir("./schedule-component/") {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir("./schedule-component/") {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir("./schedule-component/") {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir("./schedule-component/") {
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
                                configFileProvider([configFile(fileId: "3ec57b41-efe6-4628-a6c7-8be5f1c26d77", variable: "MAVEN_SETTINGS")]) {
                                    dir("./schedule-component/") {
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
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                dir("./warehouse-component/") {
                                    sh "mvn clean compile"
                                }
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                dir("./warehouse-component/") {
                                    sh "mvn test"
                                }
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                dir("./warehouse-component/") {
                                    sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                                }
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    dir("./warehouse-component/") {
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
                                configFileProvider([configFile(fileId: "3ec57b41-efe6-4628-a6c7-8be5f1c26d77", variable: "MAVEN_SETTINGS")]) {
                                    dir("./warehouse-component/") {
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
