pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
            SONAR_TOKEN = credentials('SONAR_TOKEN')
    }

    stages {
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
         stage('SonarQube Analysis') {
             steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn clean verify sonar:sonar -Dsonar.login=${SONAR_TOKEN}'
                }
             }
         }
    }
}
