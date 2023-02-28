pipeline {
    agent any
    stages {
        stage ('ACONTROL-FRONT: Download angular dependencies : npm install') {
            steps {
                sh 'rm -rf node_modules'
                sh 'npm install'
            }
        }

        stage ('ACONTROL-FRONT: Build project: ng b -c des') {
            steps {
                sh 'ng b -c des --aot'
            }
        }

        stage ('ACONTROL-FRONT: Deploying in nginx') {
            steps {
                sh 'cd dist && cp -vr . /usr/share/nginx/www/public'
            }
        }
    }
}