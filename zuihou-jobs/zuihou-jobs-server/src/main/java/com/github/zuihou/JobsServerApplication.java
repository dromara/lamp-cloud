package com.github.zuihou;

import com.github.zuihou.auth.server.EnableAuthServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

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
@Slf4j
@EnableAuthServer
public class JobsServerApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext application = SpringApplication.run(JobsServerApplication.class, args);
        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "首页: \t\thttp://{}:{}/{}\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("spring.application.name")
        );
    }
}
