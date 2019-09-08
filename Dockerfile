FROM openjdk:11

ENV VERSION=0.1.2
ENV JAR_NAME="chapp-${VERSION}.jar"

WORKDIR /opt/app

COPY target/*.jar ./

CMD ["sh", "-c", "java -jar $JAR_NAME"]
