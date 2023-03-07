pipeline {
    agent any
    stages {
        stage ('BancaMarchBACK: Import Database') {
                    steps {
                        sh 'mv bancaMarchDB_07_03_23.sql /var/lib/postgresql'
                        sh 'sudo -u postgres psql postgres < bancaMarchDB_07_03_23.sql'
            }
        }
        stage ('BancaMarchBACK: Clone DTOs and Openfeign repositories and install dependencies on BaseProyect') {
            steps {
                sh 'rm -rf ubica-cajeros-DTO'
                sh 'rm -rf ubica_cajeros_openfeign'
                sh 'git clone https://github.com/JoseCP82/ubica-cajeros-DTO.git'
                sh 'cd ubica-cajeros-DTO && mvn install'
                sh 'cd ..'
                sh 'git clone https://github.com/JoseCP82/ubica_cajeros_openfeign.git'
                sh 'cd ubica_cajeros_openfeign && mvn install'
                sh 'cd ..'
            }
        }
        stage ('BancaMarchBACK: Build project') {
            steps {
                sh 'mvn --batch-mode clean package -U -Dspring.profiles.active=${springProfile} -Dmaven.test.skip=true'
            }
        }
        stage ('BancaMarchBACK: Move and deploy on tomcat') {
            steps {
                sh 'cd target && cp ./bancaMachBackend-1.0-SNAPSHOT.war /opt/tomcat/webapps/'
                sh 'java -jar /opt/tomcat/webapps/bancaMachBackend-1.0-SNAPSHOT.war'
            }
        }
    }
}