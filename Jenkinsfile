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
        stage("build modules") {
            matrix {
                axes {
                    axis {
                        name 'MODULE'
                        values 'statistics-component','delivery-component','drone-park-component'
                    }
                }
                stages {
                    stage('build') {
                       steps {
                           echo "build"
                       }
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
