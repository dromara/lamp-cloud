<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>${projectPrefix}-${serviceName}</artifactId>
        <groupId>${pg.groupId}</groupId>
        <version>${pg.version}</version>
        <relativePath>../pom.xml</relativePath>
    </parent>

    <modelVersion>4.0.0</modelVersion>
    <artifactId>${projectPrefix}-${serviceName}-biz</artifactId>
    <name>${r"${"}project.artifactId${r"}"}</name>
    <description>${pg.description}-业务模块</description>

    <dependencies>
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-${serviceName}-entity</artifactId>
            <version>${r"${"}${projectPrefix}-project.version${r"}"}</version>
        </dependency>
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-file-sdk</artifactId>
            <version>${r"${"}${projectPrefix}-project.version${r"}"}</version>
        </dependency>
        <dependency>
            <groupId>${pg.utilGroupId}</groupId>
            <artifactId>${projectPrefix}-databases</artifactId>
        </dependency>
        <dependency>
            <groupId>${pg.utilGroupId}</groupId>
            <artifactId>${projectPrefix}-cache-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>${pg.utilGroupId}</groupId>
            <artifactId>${projectPrefix}-echo-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>${pg.utilGroupId}</groupId>
            <artifactId>${projectPrefix}-mvc</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus</artifactId>
        </dependency>
    </dependencies>
</project>
