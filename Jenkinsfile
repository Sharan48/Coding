
pipeline{
    agent any

    environment {
        MAVEN_HOME='/usr/share/maven'
        JAVA_HOME='/usr/lib/jvm/java-17-openjdk-amd64'
    }

    stages{
        stage('Checkout'){
            steps{
                git url: 'https://github.com/Sharan48/Coding.git', branch:'testing_branch'
            }
        }

        stage('Build'){
            steps{
                sh "${MAVEN_HOME}/bin/mvn clean compile"
            }
        }

        stage('Test'){
            steps{
                sh "${MAVEN_HOME}/bin/mvn -DsuiteXmlFile=testng.xml"
            }
        }

    }

    post{
        success{
            echo 'Build Passed!'
        }
        failure{
            echo 'Bild failed!'
        }
    }
}