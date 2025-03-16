@Library('python-shared-library@main') _  // Change this to match your library repository name and branch

pipeline {
    agent any
    environment {
        VENV_DIR = "venv"
    }
    stages {
        stage('Clone Repository') {
            steps {
                script {
                    python_clone_repo()  // Call the shared library function to clone the repo
                }
            }
        }
        stage('Set Up Python Environment') {
            steps {
                script {
                    python_environment()  // Call the shared library function to set up the Python environment
                }
            }
        }
        stage('poetry_lock') {
            steps {
                script {
                    poetry_lock()  // Call the shared library function to handle poetry lock
                }
            }
        }
        stage('Install Dependencies') { 
            steps {
                script {
                    python_dependencies()  // Call the shared library function to install dependencies
                }
            }
        }
        stage('Static Code Analysis') {
            steps {
                script {
                    python_static_code_analysis('SonarQube')  // Call the shared library function for static code analysis
                }
            }
        }
    }  
    post {
        always {
            script {
                echo "Cleaning up the environment"
                sh 'rm -rf venv'  // Clean up the environment after the pipeline completes
            }
            archiveArtifacts artifacts: 'coverage.txt, htmlcov/*', allowEmptyArchive: true
            echo 'Pipeline completed.'
        }
        success {
            script {
                emailext(
                    attachmentsPattern: 'coverage.txt',
                    body: """${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}
                    More info at: ${env.BUILD_URL}""",
                    to: 'jogeshwar.pradhan@mygurukulam.co',
                    subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
                )
            }
        }
        failure {
            script {
                emailext(
                    to: 'jogeshwar.pradhan@mygurukulam.co',
                    subject: 'Jenkins Pipeline Failure: Attendance API',
                    body: '''Hello,
The Jenkins pipeline has failed. Please check the logs.'''.stripIndent()
                )
            }
        }
    }
}

