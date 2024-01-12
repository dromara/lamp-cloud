#!/bin/bash
cur_dir=`pwd`

docker stop lamp_minio
docker rm lamp_minio
docker run -p 9000:9000 --name lamp_minio --restart=always \
  -d minio/minio server /data
#  -v /mnt/data:/data \
#  -v ${cur_dir}/config:/root/.minio \
