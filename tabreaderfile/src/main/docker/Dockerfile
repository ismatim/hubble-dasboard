FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD tabreaderfile-0.0.1-SNAPSHOT.jar tabreaderfile.jar
RUN sh -c 'touch /tabreaderfile.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tabreaderfile.jar" ]