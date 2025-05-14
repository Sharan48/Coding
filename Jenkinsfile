
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

    post{
        // success{
        //     echo 'Build Passed!'
        // }
        // failure{
        //     echo 'Bild failed!'
        // }
        always{
            emailtext{
                subject: "Build Result: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>Hi Team,</p>
                    <p>The test build has completed. See the details below:</p>
                    <ul>
                      <li><b>Job:</b> ${env.JOB_NAME}</li>
                      <li><b>Build Number:</b> ${env.BUILD_NUMBER}</li>
                      <li><b>Status:</b> ${currentBuild.currentResult}</li>
                      <li><b>Report:</b> <a href="${env.BUILD_URL}HTML_20Report/">Click to View Report</a></li>
                    </ul>
                    <p>Thanks,<br/>Automation Jenkins</p>
                """,
                mimeType: 'text/html',
                to: "${env.EMAIL_RECIPIENTS}"

            }
        }
    }
}
