#!/bin/bash

docker stop zuihou_nginx
docker rm zuihou_nginx
docker run -idt -p 10000:10000 --name zuihou_nginx --restart=always \
    -v /data/projects/:/data/projects \
    -v `pwd`/conf/:/etc/nginx \
    -v `pwd`/logs/:/var/log/nginx  \
    -e TZ="Asia/Shanghai" \
    nginx:1.17.0

