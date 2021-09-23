FROM openjdk:8
EXPOSE 8080
ADD target/*.jar userservice.jar
ENTRYPOINT ["java","-jar","/userservice.jar"]