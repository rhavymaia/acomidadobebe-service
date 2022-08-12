FROM openjdk:17
ADD target/acomidadobebe-service-0.0.1.jar acomidadobebe-service-0.0.1.jar
ENTRYPOINT ["java", "-jar","acomidadobebe-service-0.0.1.jar"]
EXPOSE 8080