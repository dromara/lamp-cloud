<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- 本地开发时，在bootstrap-xxx.yml中通过 logging.config=classpath:logback-spring-dev.xml 文件，表示本地的日志实时打印出来 -->
    <!-- defaults-biz-dev.xml 文件位于lamp-cloud或lamp-boot 的 lamp-public/lamp-common/src/main/resources -->
    <include resource="defaults-biz-dev.xml"/>


    <logger name="${pg.parent}.${moduleName}.controller" additivity="true" level="${r'${log.level.controller}'}">
        <appender-ref ref="CONTROLLER_APPENDER"/>
    </logger>
    <logger name="${pg.parent}.${moduleName}.service" additivity="true" level="${r'${log.level.service}'}">
        <appender-ref ref="SERVICE_APPENDER"/>
    </logger>
    <logger name="${pg.parent}.${moduleName}.manager" additivity="true" level="${r'${log.level.service}'}">
        <appender-ref ref="SERVICE_APPENDER"/>
    </logger>

    <!-- 可以在这里自定义规则 -->
</configuration>
