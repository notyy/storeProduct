FROM openjdk:8u111-jdk
MAINTAINER notyycn <notyycn@gmail.com>

COPY target/scala-2.11/storeProduct-assembly-0.0.1.jar /home/storeProduct-assembly-0.0.1.jar

EXPOSE 8080

CMD ["java","-jar","/home/storeProduct-assembly-0.0.1.jar","mode=test"]

