<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>${projectPrefix}-${serviceName}</artifactId>
        <groupId>${pg.groupId}</groupId>
        <version>${pg.version}</version>
        <relativePath>../pom.xml</relativePath>
    </parent>

    <modelVersion>4.0.0</modelVersion>
    <artifactId>${projectPrefix}-${serviceName}-entity</artifactId>
    <name>${r"${"}project.artifactId${r"}"}</name>
    <description>${pg.description}-实体模块</description>

    <dependencies>
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-common</artifactId>
            <version>${r"${"}${projectPrefix}-project.version${r"}"}</version>
        </dependency>
    </dependencies>

</project>
