
pipeline{
    agent any

    environment {
        MAVEN_HOME='/usr/share/maven'
        JAVA_HOME='/usr/lib/jvm/java-17-openjdk-amd64'
        // ENV="${params.ENV}"
        
        //Extract current brnach by removig origin/
        BRANCH_NAME="${env.GIT_BRANCH}".replaceAll('origin/','')
        //set environment based on branch login
        TEST_ENV="${env.GIT_BRNACH}".contains('coding')?'production':(env.GIT_BRANCH.contains('testing')?'staging':'qa')
    }

    // parameters{
    //     choice(name:'ENV', choices:['qa','staging','production'],description:'Select environment to run test')
    // }

    stages{
        stage('Checkout'){
            // steps{
            //     git url: 'https://github.com/Sharan48/Coding.git', branch:'testing_branch'
            // }

            steps{
                checkout([
                    $class:'GitSCM',
                    branches:[[name:"*/{env.BRANCH_NAME}"]],
                    userRemoteConfigs:[[url:'https://github.com/Sharan48/Coding.git']]
                ])
            }
            
        }

        stage('Build'){
            steps{
                sh "${MAVEN_HOME}/bin/mvn clean compile"
            }
        }

        stage('Test'){
            steps{
                // sh "${MAVEN_HOME}/bin/mvn -Denv=${ENV} test -DsuiteXmlFile=testng.xml"
                sh "${MAVEN_HOME}/bin/mvn -Denv=${env.TEST_ENV} test -DsuiteXmlFile=testng.xml"
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