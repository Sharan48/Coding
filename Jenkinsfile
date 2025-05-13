
pipeline{
    agent any

    environment {
        MAVEN_HOME='/usr/share/maven'
        JAVA_HOME='/usr/lib/jvm/java-17-openjdk-amd64'
        ENV="${params.ENV}"
    }

    parameters{
        choice(name:'ENV', choices:['qa','staging','production'],description:'Select environment to run test')
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
                sh "${MAVEN_HOME}/bin/mvn -Denv={ENV} test -DsuiteXmlFile=testng.xml"
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