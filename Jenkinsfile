pipeline {
    agent any
    stages {
        stage ('BancaMarchBACK: Build project: ') {
            steps {
                sh 'mvn --batch-mode clean package -U -P${mavenProfile} -Dspring.profiles.active=${springProfile} -Dmaven.test.skip=true'
            }
        }

        stage ('BancaMarchBACK: Move to tomcat') {
            steps {
                sh 'cd dist && cp -vr . /var/www/vps-3fdb8b00.vps.ovh.net'
            }
        }
    }
}