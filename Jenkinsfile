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
                script {
                    def tests = 'v1,v2,v3,v4,v5'.split(',')
                    for (int i = 0; i < tests.length; i++) {
                        stage("Test ${tests[i]}") {
                            //git 'https://github.com/madcocomo/jenkins-at.git/'
                            sh "echo ${tests[i]} > x.txt"
                            sh 'java -jar target/*.jar > result.txt'
                            sh 'diff x.txt result.txt'
                            post {
                              success {
                                writeFile file: tests[i], text: 'aaa'
                              }
                            }
                        }
                    }                    
                }
            }
        }
    }
}
