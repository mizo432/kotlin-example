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
        stage('build adjustment-and-support-webMvc') {
        // some block
          steps {
            echo 'building adjustment-and-support-webMvc...'      
            dir('backend') {
              sh "./gradlew :adjustment-and-support-webMvc:jar --daemon"
            }
          }
        }
        stage('build manufacturingOrProcurement-webMvc') {
        // some block
          steps {
            echo 'building manufacturingOrProcurement-webMvc...'      
            dir('backend') {
              sh "./gradlew :manufacturingOrProcurement-webMvc:jar --daemon"
            }
          }
        }
        stage('build sale-webMvc') {
        // some block
          steps {
            echo 'building sale-webMvc...'      
            dir('backend') {
              sh "./gradlew :sale-webMvc:jar --daemon"
            }
          }
        }
      }
    }
  }
}
