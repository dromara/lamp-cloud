#!/bin/bash

docker stop zuihou-nacos
docker rm zuihou-nacos
docker run -idt --name zuihou-nacos --restart=always \
        -e JVM_XMS=512m -e JVM_XMX=512m -e JVM_XMN=384m \
        -e PREFER_HOST_MODE=hostname -e MODE=standalone -e SPRING_DATASOURCE_PLATFORM=mysql \
        -e MYSQL_DATABASE_NUM=1 \
        -e MYSQL_MASTER_SERVICE_HOST=192.168.1.34 -e MYSQL_MASTER_SERVICE_DB_NAME=nacos_test -e MYSQL_MASTER_SERVICE_PORT=3306 \
        -e MYSQL_MASTER_SERVICE_USER=root \
        -e MYSQL_MASTER_SERVICE_PASSWORD="root" \
        -p 8848:8848 \
        -v `pwd`/logs/:/home/nacos/logs \
        -v `pwd`/init.d/custom.properties:/home/nacos/init.d/custom.properties \
        nacos/nacos-server:1.2.0
