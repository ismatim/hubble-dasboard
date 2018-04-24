FROM openjdk:8 AS hubble.backend.api
ADD hubble.backend.api-0.0.1-SNAPSHOT.jar hubble.backend.api-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /hubble.backend.api-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java", "-Dapp.host=mongodb","-Djava.security.egd=file:/dev/./urandom","-jar","/hubble.backend.api-0.0.1-SNAPSHOT.jar"]

