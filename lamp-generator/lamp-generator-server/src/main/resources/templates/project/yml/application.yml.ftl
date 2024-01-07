${projectPrefix}:
  swagger:
    docket:
      ${serviceName}:
        title: ${pg.description}服务
        base-package: ${pg.parent}.${moduleName}.controller
#  database:
#    initDatabasePrefix:
#      - ${projectPrefix}_base

server:
  port: ${pg.serverPort?c}


## 请在nacos中新建一个名为: ${projectPrefix}-${serviceName}-server.yml 的配置文件，并将： ${projectPrefix}-${serviceName}-server/src/main/resources/application.yml 配置文件的内容移动过去
## 然后在项目文件中，删除此文件！！！
