pipeline {
    agent any
    tools {
        maven 'M3'
    }
    stages { 
        stage('Build') {
            steps {
                sh 'mvn clean package'
                junit '**/target/surefire-reports/TEST-*.xml'
                script {
                    testJobs.runTests(this, testcases.fizzbuzz())
                }
            }
        }
    }
}

