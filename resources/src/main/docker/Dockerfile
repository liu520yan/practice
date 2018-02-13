FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD resources-1.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar