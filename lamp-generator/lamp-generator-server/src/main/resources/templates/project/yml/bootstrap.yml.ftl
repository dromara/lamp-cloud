${projectPrefix}:
  swagger:
    version: '@project.version@'
  nacos:
    ip: ${r"${"}NACOS_IP:@nacos.ip@${r"}"}
    port: ${r"${"}NACOS_PORT:@nacos.port@${r"}"}
    namespace: ${r"${"}NACOS_NAMESPACE:@nacos.namespace@${r"}"}
    username: ${r"${"}NACOS_USERNAME:@nacos.username@${r"}"}
    password: ${r"${"}NACOS_PASSWORD:@nacos.password@${r"}"}
  sentinel:
    dashboard: ${r"${SENTINEL_DASHBOARD:@sentinel.dashboard@}"}
  seata:
    ip: ${r"${"}SEATA_IP:@seata.ip@${r"}"}
    port: ${r"${"}SEATA_PORT:@seata.port@${r"}"}
    namespace: ${r"${"}SEATA_NAMESPACE:@seata.namespace@${r"}"}

spring:
  main:
    allow-bean-definition-overriding: true
  application:
    name: @project.artifactId@
    # 此参数一定要和 lamp-gateway-server.yml 文件中配置的 路由前缀(predicates参数) 一致！ （可以参考 system或base 服务）
    path: /${serviceName}
  profiles:
    active: @profile.active@
  cloud:
    sentinel:
      enabled: true
      filter:
        enabled: true
      eager: true  # 取消Sentinel控制台懒加载
      transport:
        dashboard: ${r"${"}${projectPrefix}.sentinel.dashboard${r"}"}
    nacos:
      config:
        server-addr: ${r"${"}${projectPrefix}.nacos.ip${r"}"}:${r"${"}${projectPrefix}.nacos.port${r"}"}
        file-extension: yml
        namespace: ${r"${"}${projectPrefix}.nacos.namespace${r"}"}
        shared-configs:
          - dataId: common.yml
            refresh: true
          - dataId: redis.yml
            refresh: false
          - dataId: '@database.type@'
            refresh: true
          - dataId: rabbitmq.yml
            refresh: false
        enabled: true
        username: ${r"${"}${projectPrefix}.nacos.username${r"}"}
        password: ${r"${"}${projectPrefix}.nacos.password${r"}"}
      discovery:
        username: ${r"${"}${projectPrefix}.nacos.username${r"}"}
        password: ${r"${"}${projectPrefix}.nacos.password${r"}"}
        server-addr: ${r"${"}${projectPrefix}.nacos.ip}:${r"${"}${projectPrefix}.nacos.port${r"}"}
        namespace: ${r"${"}${projectPrefix}.nacos.namespace${r"}"}
        metadata: # 元数据，用于权限服务实时获取各个服务的所有接口
          management.context-path: ${r"${"}server.servlet.context-path:${r"}"}${r"${"}spring.mvc.servlet.path:${r"}"}${r"${"}management.endpoints.web.base-path:${r"}"}
          gray_version: ${pg.author}

logging:
  file:
    path: '@logging.file.path@'
    name: ${r"${"}logging.file.path${r"}"}/${r"${"}spring.application.name}/root.log
  config: classpath:logback-spring.xml
  level:
    druid.sql.Statement: debug

# 用于/actuator/info
info:
  name: '@project.name@'
  description: '@project.description@'
  version: '@project.version@'
