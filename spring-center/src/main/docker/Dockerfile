FROM jdk:8
VOLUME /tmp
ADD *.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080