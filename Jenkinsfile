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
        stage("build modules") {
            matrix {
                axes {
                    axis {
                        name "MODULE"
                        values "statistics-component","delivery-component","drone-park-component", "invoice-component", "schedule-component", "warehouse module" , "web-services"
                    }
                }
                stages {
                    stage("Unit tests") {
                        steps {
                            echo "Unit tests module"
                            dir(MODULE) {
                                sh "mvn test"
                            }
                        }
                    }
                    stage("Artifactory Snapshot") {
                        steps {
                            configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                                dir(MODULE) {
                                    sh "mvn -s $MAVEN_SETTINGS deploy"
                                }
                            }
                        }
                    }
                }
                post {
                    success{
                        echo "====++++component successful++++===="
                    }
                    failure{
                        echo "====++++component failed++++===="
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
