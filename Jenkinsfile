pipeline {
  agent any
  stages {
    stage('common-build') {
    // some block
      steps {
        echo 'building common...'
        sh './backend/gradlew :common:build --daemon'
      }
    }
    stage('shared-build') {
    // some block
      steps {
        echo 'building shared...'      
        sh './backend/gradlew :shared-webMvc:jar --daemon'
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
