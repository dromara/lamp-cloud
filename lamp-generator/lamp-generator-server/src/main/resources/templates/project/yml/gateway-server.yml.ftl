spring:
  cloud:
    gateway:
      routes:
        # 从这里开始复制
        - id: ${serviceName}
          uri: lb://${projectPrefix}-${serviceName}-server
          predicates:
            - Path=/${serviceName}/**
          filters:
            - StripPrefix=1

## 将配置文件的内容复制到：${projectPrefix}-gateway-server.yml
## 然后删除本文件！！！
