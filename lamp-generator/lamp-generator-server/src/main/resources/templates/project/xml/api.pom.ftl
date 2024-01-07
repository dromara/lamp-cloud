<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>${pg.groupId}</groupId>
        <artifactId>${projectPrefix}-${serviceName}</artifactId>
        <version>${pg.version}</version>
        <relativePath>../pom.xml</relativePath>
    </parent>

    <modelVersion>4.0.0</modelVersion>
    <artifactId>${projectPrefix}-${serviceName}-api</artifactId>
    <name>${r"${"}project.artifactId${r"}"}</name>
    <description>${pg.description}-Api模块</description>

    <dependencies>
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-${serviceName}-entity</artifactId>
            <version>${r"${"}${projectPrefix}-project.version${r"}"}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
    </dependencies>
</project>
