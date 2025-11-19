<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>${projectPrefix}-${serviceName}</artifactId>
        <groupId>${pg.groupId}</groupId>
        <version>${r"${revision}"}</version>
        <relativePath>../pom.xml</relativePath>
    </parent>

    <modelVersion>4.0.0</modelVersion>
    <artifactId>${projectPrefix}-${serviceName}-server</artifactId>
    <name>${r"${"}project.artifactId${r"}"}</name>
    <description>${pg.description}-启动模块</description>

    <dependencies>
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-${serviceName}-controller</artifactId>
            <version>${r"${revision}"}</version>
        </dependency>
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-common-config</artifactId>
            <version>${r"${revision}"}</version>
        </dependency>
        <!-- 用户信息注入 - 微服务版实现类 -->
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-login-user-cloud-impl</artifactId>
            <version>${r"${revision}"}</version>
        </dependency>
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-oauth-api</artifactId>
            <version>${r"${revision}"}</version>
        </dependency>
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-oauth-cloud-impl</artifactId>
            <version>${r"${revision}"}</version>
        </dependency>
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-base-cloud-impl</artifactId>
            <version>${r"${revision}"}</version>
        </dependency>
        <dependency>
            <groupId>${pg.groupId}</groupId>
            <artifactId>${projectPrefix}-data-scope-sdk</artifactId>
            <version>${r"${revision}"}</version>
        </dependency>
        <dependency>
            <groupId>${pg.utilGroupId}</groupId>
            <artifactId>${projectPrefix}-all-cloud</artifactId>
        </dependency>
        <#if pg.seata?? && pg.seata>
        <!-- 想使用seata请加入以下依赖 -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>io.seata</groupId>
                    <artifactId>seata-all</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>io.seata</groupId>
                    <artifactId>seata-spring-boot-starter</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>io.seata</groupId>
            <artifactId>seata-all</artifactId>
        </dependency>
        <dependency>
            <groupId>io.seata</groupId>
            <artifactId>seata-spring-boot-starter</artifactId>
        </dependency>
        </#if>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <filters>
            <filter>../../src/main/filters/config-${r"${"}profile.active${r"}"}.properties</filter>
        </filters>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <!-- docker打包插件 -->
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>dockerfile-maven-plugin</artifactId>
                <version>${r"${"}dockerfile-maven-plugin.version${r"}"}</version>
                <configuration>
                    <repository>${r"${"}docker.image.prefix${r"}"}/${r"${"}project.artifactId${r"}"}</repository>
                    <tag>${r"${revision}"}</tag>
                    <buildArgs>
                        <JAR_FILE>target/${r"${"}project.build.finalName${r"}"}.jar</JAR_FILE>
                    </buildArgs>
                </configuration>
            </plugin>

        </plugins>
    </build>
</project>
