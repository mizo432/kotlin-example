pipeline {
  agent any
  stages {
    stage('common-build') {
    // some block
      steps {
        echo 'building common...'
        dir('backend') {
          sh "./gradlew :common:build --daemon"
        }
      }
    }
    stage('shared-build') {
    // some block
      steps {
        echo 'building shared...'      
        dir('backend') {
          sh "./gradlew :shared-webMvc:jar --daemon"
        }
      }
    }
    stage('build applications') {
      parallel {
        stage('build address-webMvc') {
        // some block
          steps {
            echo 'building address-webMvc...'      
            dir('backend') {
              sh "./gradlew :address-webMvc:jar --daemon"
            }
          }
        }
        
        stage('build relationship-webMvc') {
        // some block
          steps {
            echo 'building relationship-webMvc...'      
            dir('backend') {
              sh "./gradlew :relationship-webMvc:jar --daemon"
            }
          }
        }
      }
    }
  }
}
