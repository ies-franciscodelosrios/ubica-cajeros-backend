pipeline {
    agent any
    stages {
        stage ('BancaMarchBACK: Build project') {
            steps {
                sh 'mvn --batch-mode clean package -U -Dmaven.test.skip=true'
            }
        }
        stage ('BancaMarchBACK: Move and deploy on tomcat') {
            steps {
                sh 'cd target && mv ./bancaMachBackend-1.0-SNAPSHOT.war /opt/tomcat/webapps/'
                sh 'java -jar bancaMachBackend-1.0-SNAPSHOT.war'
            }
        }
    }
}
/*pipeline {
    agent any
    stages {
        stage ('BancaMarchBACK: Clone DTOs and Openfeign repositories and install dependencies on BaseProyect') {
            steps {
                sh 'rm -rf ubica-cajeros-DTO'
                sh 'rm -rf ubica-cajeros-openfeign'
                sh 'git clone github.com:JoseCP82/ubica-cajeros-DTO.git'
                sh 'cd ubica-cajeros-DTO'
                sh 'mvn clean package && cd ..'
                sh 'git clone github.com:JoseCP82/ubica_cajeros_openfeign.git'
                sh 'cd ubica_cajeros_openfeign'
                sh 'mvn clean package && cd ..'
            }
        }
        stage ('BancaMarchBACK: Build project') {
            steps {
                sh 'mvn --batch-mode clean package -U -Dmaven.test.skip=true'
            }
        }
        stage ('BancaMarchBACK: Move and deploy on tomcat') {
            steps {
                sh 'cd target && mv ./ubica-cajeros-backend/bancaMachBackend-1.0-SNAPSHOT.war /opt/tomcat/webapps/'
                sh 'java -jar bancaMachBackend-1.0-SNAPSHOT.war'
            }
        }
    }
}

JENKINS
pipeline {
    agent any
    stages {
        stage ('BancaMarchBACK: Clone DTOs and Openfeign repositories and install dependencies on BaseProyect') {
            steps {
                sh 'git clone https://github.com/JoseCP82/ubica-cajeros-DTO.git'
                sh 'mvn install -f ./ubica-cajeros-DTO.git/pom.xml'
                sh 'git clone https://github.com/JoseCP82/ubica_cajeros_openfeign.git'
                sh 'mvn install -f ./ubica_cajeros_openfeign.git/pom.xml'
            }
        }
        stage ('BancaMarchBACK: Build project') {
            steps {
                sh 'mvn --batch-mode clean package -U -Dmaven.test.skip=true'
            }
        }
        stage ('BancaMarchBACK: Move and deploy on tomcat') {
            steps {
                sh 'cd target && mv ./ubica-cajeros-backend/bancaMachBackend-1.0-SNAPSHOT.war /opt/tomcat/webapps/'
                sh 'java -jar bancaMachBackend-1.0-SNAPSHOT.war'
            }
        }
    }
}*/