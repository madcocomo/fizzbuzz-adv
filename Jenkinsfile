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
                sh 'git config --local credential.helper "!p() { echo username=\\$GIT_USERNAME; echo password=\\$GIT_PASSWORD; }; p"'
            }
        }
        stage('Verify') {
            steps {
                script {
                    def tests = 'v1,v2,v3,v4,v5'.split(',')
                    for (int i = 0; i < tests.length; i++) {
                        stage("Test ${tests[i]}") {
                            //git 'https://github.com/madcocomo/jenkins-at.git/'
                            writeFile file: 'x.txt', text: tests[i]
                            sh 'java -jar target/*.jar > result.txt'
                            sh 'diff x.txt result.txt'

                            sh 'git tag -f t111'
                            withCredentials([
                              usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')
                            ]) {
                              sh 'git push origin --tags'
                            }
                        }
                    }                    
                }
            }
        }
    }
}
