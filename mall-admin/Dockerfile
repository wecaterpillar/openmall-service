FROM szopen/openjdk:8-jdk-cn
#FROM szopen/openjdk:11-jdk-cn
ENV LANG C.UTF-8
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-cp","app:app/lib/*","org.openmall.mall.MallAdminApplication"]
