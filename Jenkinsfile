pipeline {
  agent any
  stages {
    stage('common-build') {
    // some block
      steps {
        echo 'building common...'
        sh './backend/gradlew --version'
      }
    }
    stage('shared-build') {
    // some block
      steps {
        echo 'building shared...'      
        sh 'cd ./backend'
        sh './gradlew --version'
      }
    }
    stage('build') {
    // some block
      steps {
        echo 'building pipeline...'      
      }
    }
  }
}
