pipeline{
    agent any
    options {
        disableConcurrentBuilds()
        timeout(time: 1, unit: "HOURS")
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
        stage("Install") {
            steps {
                echo "Compile module"
                sh "mvn clean compile"
            }
        }
        stage("statistics-component"){
            steps {
                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                    dir("statistics-component") {
                        echo "Unit tests module"
                        sh "mvn test"
                        echo "Deployment into artifactory"
                        sh "mvn -s $MAVEN_SETTINGS deploy"
                    }
                }
            }
        }
        stage("delivery-component"){
            steps {
                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                    dir("delivery-component") {
                        echo "Unit tests module"
                        sh "mvn test"
                        echo "Deployment into artifactory"
                        sh "mvn -s $MAVEN_SETTINGS deploy"
                    }
                }
            }
        }
        stage("drone-park-componentt"){
            steps {
                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                    dir("drone-park-component") {
                        echo "Unit tests module"
                        sh "mvn test"
                        echo "Deployment into artifactory"
                        sh "mvn -s $MAVEN_SETTINGS deploy"
                    }
                }
            }
        }
        stage("invoice-component"){
            steps {
                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                    dir("invoice-component") {
                        echo "Unit tests module"
                        sh "mvn test"
                        echo "Deployment into artifactory"
                        sh "mvn -s $MAVEN_SETTINGS deploy"
                    }
                }
            }
        }
        stage("schedule-component"){
            steps {
                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                    dir("schedule-component") {
                        echo "Unit tests module"
                        sh "mvn test"
                        echo "Deployment into artifactory"
                        sh "mvn -s $MAVEN_SETTINGS deploy"
                    }
                }
            }
        }
        stage("warehouse-component"){
            steps {
                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                    dir("warehouse-component") {
                        echo "Unit tests module"
                        sh "mvn test"
                        echo "Deployment into artifactory"
                        sh "mvn -s $MAVEN_SETTINGS deploy"
                    }
                }
            }
        }
        stage("web-services"){
            steps {
                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                    dir("web-services") {
                        echo "Unit tests module"
                        sh "mvn test"
                        echo "Deployment into artifactory"
                        sh "mvn -s $MAVEN_SETTINGS deploy"
                    }
                }
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
                catchError(buildResult: "SUCCESS", stageResult: "FAILURE") {
                    timeout(time: 1, unit: "HOURS") {
                        waitForQualityGate true
                    }
                }
            }
        }
    }
    post{
        success {
            echo "======== pipeline executed successfully ========"
        }
        failure {
            echo "======== pipeline execution failed========"
        }
    }
}
