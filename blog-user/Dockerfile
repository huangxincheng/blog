# FROM fabric8/java-jboss-openjdk8-jdk
# FROM frolvlad/alpine-oraclejdk8:slim
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD blog-user.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8082