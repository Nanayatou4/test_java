pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
            SONAR_TOKEN = credentials('SONAR_TOKEN')
    }

    stages {
        stage('Setup Maven Settings') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'nexus-creds',
                    usernameVariable: 'NEXUS_USER',
                    passwordVariable: 'NEXUS_PASS'
                )]) {
                    sh '''
                        mkdir -p ~/.m2
                        rm -f ~/.m2/settings.xml

                        cat > ~/.m2/settings.xml <<'EOF'
<settings>
  <servers>
    <server>
      <id>nexus-snapshots</id>
      <username>${NEXUS_USER}</username>
      <password>${NEXUS_PASS}</password>
    </server>
    <server>
      <id>nexus-releases</id>
      <username>${NEXUS_USER}</username>
      <password>${NEXUS_PASS}</password>
    </server>
  </servers>
</settings>
EOF
                    '''
                }
            }
        }

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

        stage('Publish to Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    }
}