package com.github.zuihou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * 消息服务
 *
 * @author zuihou
 * @createTime 2018-01-25 10:13
 */
@SpringBootApplication
@ComponentScan({
        "com.github.zuihou",
        "com.xxl.job.admin",
})
@MapperScan(
        basePackages = {
                "com.xxl.job.admin.dao",
        },
        annotationClass = Repository.class)
public class JobsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobsServerApplication.class, args);
    }
}
