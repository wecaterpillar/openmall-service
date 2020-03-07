FROM szopen/openjdk:8-jdk-cn
#FROM szopen/openjdk:11-jdk-cn
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /app/app.jar
