stages {
        stage ('test') {
            steps {                                                             /// stage 1 for test
                sh 'mvn test'
            }
        }

        stage ('build') {
            steps {
                sh 'mvn build'                                                  /// stage 2 for build
            }
        }
        stage ('deploy') {
            steps {
                sh 'mvn deploy'                                                 /// stage 3 for deploy
            }
        }
    }
}


pipeline {
    agent any

    environment {
        // Define variables to store credentials
        MY_USERNAME = credentials('my-username-id')
        MY_PASSWORD = credentials('my-password-id')
    }

    stages {
        stage('Example Stage') {
            steps {
                script {
                    // Use credentials within this block
                    withCredentials([usernamePassword(credentialsId: 'my-credentials-id', passwordVariable: 'MY_PASSWORD', usernameVariable: 'MY_USERNAME')]) {
                        // Your build steps that require credentials go here
                        echo "Username: ${MY_USERNAME}"
                        echo "Password: ${MY_PASSWORD}"
                    }
                }
            }
        }
    }
}
