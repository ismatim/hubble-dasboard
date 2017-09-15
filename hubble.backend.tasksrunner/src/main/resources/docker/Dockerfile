FROM openjdk:8 AS hubble-tasksrunner
ADD hubble.backend.tasksrunner-1.0-SNAPSHOT.jar hubble.backend.tasksrunner-1.0-SNAPSHOT.jar
RUN sh -c 'touch /hubble.backend.tasksrunner-1.0-SNAPSHOT.jar'  
ENTRYPOINT ["java", "-Dapp.host=mongodb","-Djava.security.egd=file:/dev/./urandom","-jar","/hubble.backend.tasksrunner-1.0-SNAPSHOT.jar"]
