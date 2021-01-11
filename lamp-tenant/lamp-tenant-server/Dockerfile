FROM openjdk:8-jre
MAINTAINER zuihou 306479353@qq.com

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone

ARG JAR_FILE
COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java", "-Xmx512m", "-Djava.security.egd=file:/dev/./urandom", "-Ddruid.mysql.usePingMethod=false", "-jar", "/app.jar"]
CMD ["--spring.profiles.active=docker"]
