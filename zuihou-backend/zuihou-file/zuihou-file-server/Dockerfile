FROM reg.zkhy.com:1443/library/openjdk:8-jdk
MAINTAINER zuihou zuihou@zkhy.com

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone

COPY target/zuihou-file-server.jar /app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--spring.profiles.active=docker"]
