def call(String sonarServerName) {
    withSonarQubeEnv(sonarServerName) {
        def scannerHome = tool name: 'Sonar', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
        sh """
            ${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=attendance \
            -Dsonar.projectName=attendance-api \
            -Dsonar.java.binaries=target/classes \
            -Dsonar.issue.types=CODE_SMELL \
            -Dsonar.report.export.path=target/static-code-analysis-report.json
        """
    }
}
