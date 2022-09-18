node {
    def mvnHome
    stage('Clone') { // for display purposes
         cleanWs()
    
        // Get some code from a GitHub repository
        git branch: 'main',
            url: 'https://github.com/rudyHoarau/FR-MEDHEAD-POC-URGENCE.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'M3'
    }
    stage('build-hopital') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
				sh '''cd hospital
                    $MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean install\''''
            } else {
				bat '''cd hospital
				    "%MVN_HOME%\\bin\\mvn" -Dmaven.test.failure.ignore clean install'''
            }
        }
    }
	stage('build-urgence') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
				sh '''cd urgence
                    $MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean install\''''
            } else {
				bat '''cd urgence
				    "%MVN_HOME%\\bin\\mvn" -Dmaven.test.failure.ignore clean install'''
            }
        }
    }
    stage('test-hopital') {
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '''cd hospital
                    $MVN_HOME/bin/mvn" surefire-report:report\''''
            } else {
                bat '''cd hospital
				    "%MVN_HOME%\\bin\\mvn" surefire-report:report'''
            }
        }
        
        junit 'hospital/target/surefire-reports/TEST-*.xml'
    }
    stage('test-urgence') {
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '''cd urgence
                    $MVN_HOME/bin/mvn" surefire-report:report\''''
            } else {
                bat '''cd urgence
				    "%MVN_HOME%\\bin\\mvn" surefire-report:report'''
            }
        }
        
        junit 'urgence/target/surefire-reports/TEST-*.xml'
    }
}
