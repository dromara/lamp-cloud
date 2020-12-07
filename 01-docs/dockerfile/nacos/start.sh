#!/bin/bash

docker stop lamp_nacos
docker rm lamp_nacos
docker run -idt --name lamp_nacos --restart=always \
        -e JVM_XMS=512m -e JVM_XMX=512m -e JVM_XMN=384m \
        -e PREFER_HOST_MODE=hostname -e MODE=standalone -e SPRING_DATASOURCE_PLATFORM=mysql \
        -e MYSQL_DATABASE_NUM=1 \
        -e MYSQL_SERVICE_HOST=192.168.2.178 -e MYSQL_SERVICE_DB_NAME=lamp_nacos -e MYSQL_SERVICE_PORT=3218 \
        -e MYSQL_SERVICE_USER=root \
        -e MYSQL_SERVICE_PASSWORD=root \
        -p 8848:8848 \
        -v `pwd`/logs/:/home/nacos/logs \
        -v `pwd`/init.d/custom.properties:/home/nacos/init.d/custom.properties \
        nacos/nacos-server:1.3.1
