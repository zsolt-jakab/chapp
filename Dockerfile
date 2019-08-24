FROM openjdk:11

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/chapp-0.1.2.jar"]

ADD target/chapp-0.1.2.jar /opt/chapp-0.1.2.jar