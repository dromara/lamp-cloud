package com.github.zuihou.file.config;

/**
 * @author zuihou
 * @createTime 2018-01-25 22:48
 */

import com.github.zuihou.core.spring.autoconfigure.datasource.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取yml配置的信息
 * @author zuihou
 */
@ConfigurationProperties(
        prefix = "zuihou.mysql.file"
)
public class FileDataSourceProperties extends DataSourceProperties {

}
