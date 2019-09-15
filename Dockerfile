FROM openjdk:11

WORKDIR /opt/app

COPY target/*.jar ./service.jar

CMD ["java", "-jar", "service.jar"]
