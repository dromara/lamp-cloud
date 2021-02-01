docker stop kkfileview
docker rm kkfileview
docker run --name kkfileview --restart=always -p 8012:8012 \
  -v `pwd`/application.properties:/opt/kkFileView-2.2.0-SNAPSHOT/config/application.properties \
  -d keking/kkfileview
