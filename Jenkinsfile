pipeline{
  agent any
tools {
        maven "Maven"
    }

  stages{
        stage('Build'){
            steps{
                bat "mvn -f 'MoniteurBusMicroservices/api-gateway/pom.xml' -DskipTests  clean install"
                bat "mvn -f 'MoniteurBusMicroservices/eureka-server/pom.xml' -DskipTests  clean install"
                bat "mvn -f 'MoniteurBusMicroservices/EcoleMicroservice/pom.xml' -DskipTests  clean install"
                bat "mvn -f 'MoniteurBusMicroservices/EtudiantMicroservice/pom.xml' -DskipTests  clean install"
                bat "mvn -f 'MoniteurBusMicroservices/BusMicroservice/pom.xml' -DskipTests  clean install"
                bat "mvn -f 'MoniteurBusMicroservices/LocationMicroservice/pom.xml' -DskipTests  clean install"
                bat "mvn -f 'MoniteurBusMicroservices/RealTimeLocalisationMicroservice/pom.xml' -DskipTests  clean install"
                bat "mvn -f 'MoniteurBusMicroservices/UtilisateurMicroservice/pom.xml' -DskipTests  clean install"
            }
        }
        stage('Test Ecole'){
              steps{
                  bat "mvn -f 'MoniteurBusMicroservices/EcoleMicroservice/pom.xml' test -Dtest=EcoleServiceTest"
                  bat "mvn -f 'MoniteurBusMicroservices/EcoleMicroservice/pom.xml' test -Dtest=EcoleControllerTest"
              }
        }
        stage('Test Utilisateur'){
                      steps{
                          bat "mvn -f 'MoniteurBusMicroservices/UtilisateurMicroservice/pom.xml' test -Dtest=UtilisateurControllerTest"
                          bat "mvn -f 'MoniteurBusMicroservices/UtilisateurMicroservice/pom.xml' test -Dtest=UtilisateurServiceTest"
                      }
        }
        stage('Test Etudiant'){
                      steps{
                          bat "mvn -f 'MoniteurBusMicroservices/EtudiantMicroservice/pom.xml' test -Dtest=EtudiantControllerTest"
                          bat "mvn -f 'MoniteurBusMicroservices/EtudiantMicroservice/pom.xml' test -Dtest=EtudiantServiceTest"
                      }
        }
  }
}