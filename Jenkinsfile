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
                    def success = false
                    for (test in tests) {
                        stage("Test ${test}") {
                            //git 'https://github.com/madcocomo/jenkins-at.git/'
                            sh "echo ${test} > expect"
                            sh 'java -jar target/*.jar > result'
                            sh 'diff expect result'

                            sh "git tag ${BRANCH_NAME}-pass-${test}-${BUILD_ID}"
                            withCredentials([
                              usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')
                            ]) {
                              sh 'git push origin --tags'
                            }
                            success = true
                        }
                        if (success) break;
                    }                    
                }
            }
        }
    }
}
