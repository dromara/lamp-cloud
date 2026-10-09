package top.tangyh.lamp.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 服务启动工具类
 *
 * @author zuihou
 */
@Slf4j
public class ServerApplication {

    protected static void start(Class<?> primarySource, String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(primarySource, args);
        Environment env = application.getEnvironment();
        String msg = """
                
                ----------------------------------------------------------
                应用 '{}' 启动成功， JDK版本号：{} ！
                NextDoc4j文档（支持gateway服务聚合文档）: http://{}:{}{}/doc.html
                swagger原始文档（不支持gateway服务聚合文档）：http://{}:{}{}/swagger-ui.html
                数据库监控（可用于排查数据源是否链接成功）:   http://{}:{}/druid
                当前环境变量：{} 日志路径：{}
                ----------------------------------------------------------
                """;

        log.info(msg,
                env.getProperty("spring.application.name"),
                env.getProperty("java.version"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path", ""),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path", ""),
                "127.0.0.1",
                env.getProperty("server.port"),
                env.getProperty("spring.profiles.active"), env.getProperty("LOG_PATH")
        );
    }
}
