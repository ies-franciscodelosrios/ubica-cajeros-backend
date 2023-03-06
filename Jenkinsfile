pipeline {
    agent any
    stages {
        stage ('BancaMarchBACK: Build project: ') {
            steps {
                sh 'mvn --batch-mode clean package -U -P${mavenProfile} -Dspring.profiles.active=${springProfile} -Dmaven.test.skip=true'
            }
        }

        stage ('BancaMarchBACK: Move and deploy on tomcat') {
            steps {
                sh 'cd target && cp -vr . /var/lib/tomcat9/webapps/ROOT'
            }
        }
    }
}