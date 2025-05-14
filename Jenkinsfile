
pipeline{
    agent any

    environment {
        MAVEN_HOME='/usr/share/maven'
        JAVA_HOME='/usr/lib/jvm/java-17-openjdk-amd64'
        ENV='qa'
        // ENV="${params.ENV}
        EMAIL_RECIPIENTS='sharan4748@gmail.com'

    }

    // parameters{
    //     choice(name:'ENV', choices:['qa','staging','production'],description:'Select environment to run test')
    // }

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
                sh "${MAVEN_HOME}/bin/mvn -Denv=${ENV} test -DsuiteXmlFile=testng.xml,testng-parallel.xml"
                
            }
        }

        stage('Archive Test Rsults'){
            steps{
                archiveArtifacts artifacts: 'target/surefire-reports/*.xml', fingerprint:true
            }
        }

    }

   post {
    always {
        emailext(
            subject: "Automation Reports - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: "Test completed. Please check the attached report.",
            to: "${EMAIL_RECIPIENTS}",
            attachmentsPattern: "**/target/surefire-reports/*.html",
            mimeType: 'text/html'
        )
    }
}

}
