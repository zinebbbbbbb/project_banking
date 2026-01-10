pipeline {
	agent any

	stages {
		stage('Checkout') {
			steps{
                checkout scm
			}
		}
		stage('Build Images') {
			steps{
               sh 'docker compose build'
			}
		}
		stage('Run docker') {
			steps{
                 sh 'docker compose up-d'
			}
		}

		post{
			always {
				sh 'docker compose ps'
			}
		}

	}
}