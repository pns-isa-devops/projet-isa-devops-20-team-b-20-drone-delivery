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
                    agent {
                        node {
                            label ''
                            customWorkspace './statistics-component/'
                        }
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                sh "mvn clean compile"
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                sh "mvn test"
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
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
                                        sh "mvn -s $MAVEN_SETTINGS deploy"
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
                    agent {
                        node {
                            label ''
                            customWorkspace './delivery-component/'
                        }
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                sh "mvn clean compile"
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                sh "mvn test"
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
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
                                    sh "mvn -s $MAVEN_SETTINGS deploy"
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
                    agent {
                        node {
                            label ''
                            customWorkspace './drone-park-component/'
                        }
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                sh "mvn clean compile"
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                sh "mvn test"
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
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
                                    sh "mvn -s $MAVEN_SETTINGS deploy"
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
                    agent {
                        node {
                            label ''
                            customWorkspace './invoice-component/'
                        }
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                sh "mvn clean compile"
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                sh "mvn test"
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
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
                                    sh "mvn -s $MAVEN_SETTINGS deploy"
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
                    agent {
                        node {
                            label ''
                            customWorkspace './web-services/'
                        }
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                sh "mvn clean compile"
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                sh "mvn test"
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
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
                                    sh "mvn -s $MAVEN_SETTINGS deploy"
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
                    agent {
                        node {
                            label ''
                            customWorkspace './schedule-component/'
                        }
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                sh "mvn clean compile"
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                sh "mvn test"
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
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
                                    sh "mvn -s $MAVEN_SETTINGS deploy"
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
                    agent {
                        node {
                            label ''
                            customWorkspace './warehouse-component/'
                        }
                    }
                    stages  {
                        stage("Install") {
                            steps {
                                echo "Compile module"
                                sh "mvn clean compile"
                            }
                        }
                        stage("Unit tests") {
                            steps {
                                echo "Unit tests module"
                                sh "mvn test"
                            }
                        }
                        stage("Test Mutation") {
                            steps {
                                echo "Mutation tests"
                                sh "mvn install org.pitest:pitest-maven:mutationCoverage"
                            }
                        }
                        stage("Sonar analysis") {
                            steps {
                                echo "Sonar code analysis"
                                withSonarQubeEnv("Sonarqube_env") {
                                    sh "mvn install sonar:sonar -Dsonar.pitest.mode=reuseReport"
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
                                    sh "mvn -s $MAVEN_SETTINGS deploy"
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
