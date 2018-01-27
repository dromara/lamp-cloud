package com.github.zuihou.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.util.Arrays;

/**
 * dozer 配置属性
 * @author zuihou
 * @createTime 2017-11-23 16:25
 */
@ConfigurationProperties(prefix = "dozer")
public class DozerConfigurationProperties {

    /**
     * Mapping files configuration.
     * For example <code>classpath:*.dozer.xml</code>.
     */
    private Resource[] mappingFiles = new Resource[]{};

    /**
     * Mapping files configuration.
     *
     * @return mapping files
     */
    public Resource[] getMappingFiles() {
        return Arrays.copyOf(mappingFiles, mappingFiles.length);
    }

    /**
     * Set mapping files configuration. For example <code>classpath:*.dozer.xml</code>.
     *
     * @param mappingFiles dozer mapping files
     * @return dozer properties
     */
    public DozerConfigurationProperties setMappingFiles(Resource[] mappingFiles) {
        this.mappingFiles = Arrays.copyOf(mappingFiles, mappingFiles.length);
        return this;
    }
}
