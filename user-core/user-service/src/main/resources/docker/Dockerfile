FROM oracle-java8
MAINTAINER SEALAB
WORKDIR /opt/software
ADD aircraft-service-1.0.0.jar aircraft-service.jar
ENTRYPOINT ["java", "-jar", "aircraft-service.jar"]