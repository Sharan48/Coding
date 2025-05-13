
// pipeline{
//     agent any

//     environment {
//         MAVEN_HOME='/usr/share/maven'
//         JAVA_HOME='/usr/lib/jvm/java-17-openjdk-amd64'
//         // ENV="${params.ENV}"
//         // Set a placeholder – required by declarative syntax
//         BRANCH_NAME= ''
//         TEST_ENV= ''

//     }

//     // parameters{
//     //     choice(name:'ENV', choices:['qa','staging','production'],description:'Select environment to run test')
//     // }

//     stages{
       
//        stage('Set Environment'){
//         steps{
//             script{
//                 //clean up GIT_BRNACH to remove 'origin/'
//                 env.BRANCH_NAME="${env.GIT_BRANCH}".replaceAll('origin/','')

//                 //Dynamically set TEST_ENV based on branch name

//                 if(env.BRANCH_NAME.contains('testing')){
//                     env.TEST_ENV='qa'
//                 }else if(env.BRANCH_NAME.contains('coding')){
//                     env.TEST_ENV='staging'

//                 }else{
//                     env.TEST_ENV='production'
//                 }

//                 echo "branch : ${env.BRANCH_NAME}"
//                 echo "test environment : ${env.TEST_ENV}"

//             }
//         }
//        }

//         stage('Checkout'){
//             // steps{
//             //     git url: 'https://github.com/Sharan48/Coding.git', branch:'testing_branch'
//             // }

//             steps{
//                 checkout([
//                     $class:'GitSCM',
//                     branches:[[name:"*/${env.BRANCH_NAME}"]],
//                     userRemoteConfigs:[[url:'https://github.com/Sharan48/Coding.git']]
//                 ])
//             }
            
//         }

//         stage('Build'){
//             steps{
//                 sh "${MAVEN_HOME}/bin/mvn clean compile"
//             }
//         }

//         stage('Test'){
//             steps{
//                 // sh "${MAVEN_HOME}/bin/mvn -Denv=${ENV} test -DsuiteXmlFile=testng.xml"
//                 sh "${MAVEN_HOME}/bin/mvn -Denv=${env.TEST_ENV} test -DsuiteXmlFile=testng.xml"
//             }
//         }

//     }

//     post{
//         success{
//             echo 'Build Passed!'
//         }
//         failure{
//             echo 'Bild failed!'
//         }
//     }
// }
pipeline {
    agent any

    environment {
        MAVEN_HOME = '/usr/share/maven'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        BRANCH_NAME = ''
        TEST_ENV = ''
    }

    stages {
        stage('Set Environment') {
            steps {
                script {
                    // Safely determine the raw branch name
                    def rawBranch = env.GIT_BRANCH
                    if (!rawBranch) {
                        rawBranch = sh(
                            script: "git rev-parse --abbrev-ref HEAD",
                            returnStdout: true
                        ).trim()
                    }

                    // Clean 'origin/' prefix and store in env.BRANCH_NAME
                    env.BRANCH_NAME = rawBranch?.replaceAll('origin/', '')

                    // Safely check and assign TEST_ENV
                    if (env.BRANCH_NAME?.contains('testing')) {
                        env.TEST_ENV = 'qa'
                    } else if (env.BRANCH_NAME?.contains('coding')) {
                        env.TEST_ENV = 'staging'
                    } else {
                        env.TEST_ENV = 'production'
                    }

                    echo "Branch: ${env.BRANCH_NAME}"
                    echo "Test Environment: ${env.TEST_ENV}"
                }
            }
        }

        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "*/${env.BRANCH_NAME}"]],
                    userRemoteConfigs: [[url: 'https://github.com/Sharan48/Coding.git']]
                ])
            }
        }

        stage('Build') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean compile"
            }
        }

        stage('Test') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn -Denv=${env.TEST_ENV} -DsuiteXmlFile=testng.xml test"
            }
        }
    }

    post {
        success {
            echo 'Build Passed!'
        }
        failure {
            echo 'Build Failed!'
        }
    }
}
