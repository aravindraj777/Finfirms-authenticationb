FROM openjdk:17
EXPOSE 8090
ADD target/finfirms-authentication finfirms-authentication.jar
ENTRYPOINT ["java","-jar","/finfirms-authentication.jar"]