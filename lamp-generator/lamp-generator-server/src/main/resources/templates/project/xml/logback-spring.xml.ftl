<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- defaults-biz-prod.xml 文件位于lamp-cloud或lamp-boot 的 lamp-public/lamp-common/src/main/resources -->
    <include resource="defaults-biz-prod.xml"/>

    <logger name="${pg.parent}.${moduleName}.controller" additivity="true" level="${r'${log.level.controller}'}">
        <appender-ref ref="ASYNC_CONTROLLER_APPENDER"/>
    </logger>
    <logger name="${pg.parent}.${moduleName}.service" additivity="true" level="${r'${log.level.service}'}">
        <appender-ref ref="ASYNC_SERVICE_APPENDER"/>
    </logger>
    <logger name="${pg.parent}.${moduleName}.manager" additivity="true" level="${r'${log.level.service}'}">
        <appender-ref ref="ASYNC_SERVICE_APPENDER"/>
    </logger>

    <!-- 可以在这里自定义规则 -->
</configuration>
