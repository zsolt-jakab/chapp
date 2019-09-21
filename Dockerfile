FROM openjdk:11

EXPOSE 8080

WORKDIR /opt/app

COPY target/*.jar ./service.jar

CMD ["java", "-jar", "service.jar"]
