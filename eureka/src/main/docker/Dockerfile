FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD eureka-0.0.1-SNAPSHOT.jar eureka.jar
RUN sh -c 'touch /eureka.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /eureka.jar" ]