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
    stage('build applications') {
      parallel {
        stage('build address-webMvc') {
        // some block
          steps {
            echo 'building address-webMvc...'      
          sh './backend/gradlew :address-webMvc:jar --daemon'
          }
        }
        stage('build relationship-webMvc') {
        // some block
          steps {
            echo 'building relationship-webMvc...'      
          sh './backend/gradlew :relationship-webMvc:jar --daemon'
          }
        }
      }
    }
  }
}
