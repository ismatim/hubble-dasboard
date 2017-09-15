FROM openjdk:8
ADD hubble.frontend.web-0.0.1-SNAPSHOT.jar hubble.frontend.web-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /hubble.frontend.web-0.0.1-SNAPSHOT.jar'  
ENTRYPOINT ["java", "-Dapp.host=mongodb","-Djava.security.egd=file:/dev/./urandom","-jar","/hubble.frontend.web-0.0.1-SNAPSHOT.jar"]
