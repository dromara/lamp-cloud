package top.tangyh.lamp.generator.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zuihou
 * @date 2022/3/9 21:40
 */
@Data
@NoArgsConstructor
public class MapperConfig {

    /**
     * 格式化Mapper文件名称
     */
    private String formatMapperFileName;
    /**
     * 格式化Xml文件名称
     */
    private String formatXmlFileName;
    /**
     * 是否添加 @Mapper 注解（默认 false）
     *
     * @since 3.5.1
     */
    private Boolean mapperAnnotation = false;
    /**
     * mapper类上是否添加忽略表结构 @InterceptorIgnore
     */
    private Set<String> columnAnnotationTablePrefix = new HashSet();
    /**
     * 是否开启BaseResultMap（默认 false）
     *
     * @since 3.5.0
     */
    private Boolean baseResultMap = true;

    /**
     * 是否开启baseColumnList（默认 false）
     *
     * @since 3.5.0
     */
    private Boolean baseColumnList = true;

    /**
     * 是否启用mybatis二级缓存
     *
     * @since 3.5.0
     */
    private boolean cache = false;
    /**
     * 设置缓存实现类
     *
     * @since 3.5.0
     */
    private Class<? extends Cache> cacheClass = LoggingCache.class;


}
