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
            }
        }
        stage('Verify') {
            steps {
                git 'https://github.com/madcocomo/jenkins-at.git/'
                sh 'java -jar target/*.jar > result.txt'
                sh 'diff 1.txt result.txt'
            }
        }
    }
}
