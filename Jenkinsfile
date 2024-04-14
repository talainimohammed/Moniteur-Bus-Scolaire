pipeline{
  agent any
tools {
        maven "Maven"
    }

  stages{
        stage('Build'){
            steps{
                    bat 'mvn -f 'MoniteurBus Microservices/api-gateway/pom.xml' -DskipTests  clean install'
                    bat 'mvn -f 'MoniteurBus Microservices/eureka-server/pom.xml' -DskipTests  clean install'
                    bat 'mvn -f 'MoniteurBus Microservices/EcoleMicroservice/pom.xml' -DskipTests  clean install'
                    bat 'mvn -f 'MoniteurBus Microservices/EtudiantMicroservice/pom.xml' -DskipTests  clean install'
                    bat 'mvn -f 'MoniteurBus Microservices/BusMicroservice/pom.xml' -DskipTests  clean install'
                    bat 'mvn -f 'MoniteurBus Microservices/LocationMicroservice/pom.xml' -DskipTests  clean install'
                    bat 'mvn -f 'MoniteurBus Microservices/RealTimeLocalisationMicroservice/pom.xml' -DskipTests  clean install'
                    bat 'mvn -f 'MoniteurBus Microservices/UtilisateurMicroservice/pom.xml' -DskipTests  clean install'
            }
        }
        stage('Test Ecole'){
              steps{
                  bat 'mvn -f 'MoniteurBus Microservices/EcoleMicroservice/pom.xml' test -Dtest=EcoleServiceTest'
                  bat 'mvn -f 'MoniteurBus Microservices/EcoleMicroservice/pom.xml' test -Dtest=EcoleControllerTest'
              }
        }
        stage('Test Utilisateur'){
                      steps{
                          bat 'mvn -f 'MoniteurBus Microservices/UtilisateurMicroservice/pom.xml' test -Dtest=UtilisateurControllerTest'
                          bat 'mvn -f 'MoniteurBus Microservices/UtilisateurMicroservice/pom.xml' test -Dtest=UtilisateurServiceTest'
                      }
        }
        stage('Test Etudiant'){
                      steps{
                          bat 'mvn -f 'MoniteurBus Microservices/EtudiantMicroservice/pom.xml' test -Dtest=EtudiantControllerTest'
                          bat 'mvn -f 'MoniteurBus Microservices/EtudiantMicroservice/pom.xml' test -Dtest=EtudiantServiceTest'
                      }
        }
  }
}