package top.tangyh.lamp.generator.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.request.DownloadVO;
import top.tangyh.basic.boot.config.BaseConfig;
import top.tangyh.basic.boot.handler.AbstractGlobalExceptionHandler;
import top.tangyh.basic.database.config.BaseMybatisConfiguration;
import top.tangyh.basic.database.properties.DatabaseProperties;
import top.tangyh.basic.database.properties.MultiTenantType;
import top.tangyh.basic.log.event.SysLogListener;
import top.tangyh.basic.utils.DateUtils;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.basic.validator.annotation.EnableFormValidator;
import top.tangyh.lamp.base.service.system.BaseOperationLogService;
import top.tangyh.lamp.common.api.LogApi;
import top.tangyh.lamp.common.constant.BizConstant;
import top.tangyh.lamp.datascope.interceptor.DataScopeInnerInterceptor;
import top.tangyh.lamp.generator.enumeration.ProjectTypeEnum;
import top.tangyh.lamp.generator.vo.save.ProjectGeneratorVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static top.tangyh.lamp.generator.utils.GenCodeConstant.API_SERVICE_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.APPLICATION_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.BIZ_SERVICE_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.BOOTSTRAP_DEV_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.BOOTSTRAP_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.CONTROLLER_SERVICE_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.ENTITY_SERVICE_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.EXCEPTION_CONFIGURATION_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.GATEWAY_SERVER_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.JAVA_FORMAT;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.LOGBACK_SPRING_DEV_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.LOGBACK_SPRING_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.POM_FORMAT;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.POM_NAME;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.RESOURCE_XML_FORMAT;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.RESOURCE_YML_FORMAT;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.ROOT;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.RUN_APPLICATION_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.SERVER_SERVICE_SUFFIX;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.SRC_MAIN_JAVA;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.SRC_MAIN_RESOURCE;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.SRC_TEST_JAVA;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.SRC_TEST_RESOURCE;
import static top.tangyh.lamp.generator.utils.GenCodeConstant.WEB_CONFIGURATION_SUFFIX;

/**
 * 项目生成 工具类
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/4/2 7:35 PM
 * @create [2022/4/2 7:35 PM ] [tangyh] [初始创建]
 */
@Slf4j
public class ProjectUtils {

    private final static String[] MAVEN_PATH = new String[]{
            SRC_MAIN_JAVA, SRC_MAIN_RESOURCE, SRC_TEST_JAVA, SRC_TEST_RESOURCE
    };

    private final static List<String> CLOUD_MODULE = CollUtil.newArrayList(
            API_SERVICE_SUFFIX, ENTITY_SERVICE_SUFFIX, BIZ_SERVICE_SUFFIX,
            CONTROLLER_SERVICE_SUFFIX, SERVER_SERVICE_SUFFIX
    );
    private final static List<String> BOOT_MODULE = CollUtil.newArrayList(
            ENTITY_SERVICE_SUFFIX, BIZ_SERVICE_SUFFIX, CONTROLLER_SERVICE_SUFFIX
    );
    private final static Map<ProjectTypeEnum, List<String>> TYPE_MODULE_MAP = new HashMap();

    static {
        TYPE_MODULE_MAP.put(ProjectTypeEnum.CLOUD, CLOUD_MODULE);
        TYPE_MODULE_MAP.put(ProjectTypeEnum.BOOT, BOOT_MODULE);
    }

    private static Map<String, Object> getImportPackages(ProjectGeneratorVO vo, DatabaseProperties databaseProperties) {
        Map<String, Object> map = new HashMap<>();
        Set<String> applicationImport = new TreeSet<>();
        applicationImport.add(EnableFormValidator.class.getCanonicalName());
        applicationImport.add(BizConstant.class.getCanonicalName());

        Set<String> webConfigurationImport = new TreeSet<>();
        if (ProjectTypeEnum.CLOUD.eq(vo.getType())) {
            webConfigurationImport.add(LogApi.class.getCanonicalName());
        } else {
            webConfigurationImport.add(BaseOperationLogService.class.getCanonicalName());
        }
        webConfigurationImport.add(BaseConfig.class.getCanonicalName());
        webConfigurationImport.add(SysLogListener.class.getCanonicalName());

        Set<String> exceptionConfigurationImport = new TreeSet<>();
        exceptionConfigurationImport.add(AbstractGlobalExceptionHandler.class.getCanonicalName());

        Set<String> mybatisConfigurationImport = new TreeSet<>();
        mybatisConfigurationImport.add(BaseMybatisConfiguration.class.getCanonicalName());
        mybatisConfigurationImport.add(DataScopeInnerInterceptor.class.getCanonicalName());
        mybatisConfigurationImport.add(DatabaseProperties.class.getCanonicalName());
        mybatisConfigurationImport.add(InnerInterceptor.class.getCanonicalName());

        if (MultiTenantType.NONE.eq(databaseProperties.getMultiTenantType())) {
            mybatisConfigurationImport.add(MapperScan.class.getCanonicalName());
            mybatisConfigurationImport.add(Repository.class.getCanonicalName());
        }

        map.put("applicationImport", applicationImport);
        map.put("webConfigurationImport", webConfigurationImport);
        map.put("exceptionConfigurationImport", exceptionConfigurationImport);
        map.put("mybatisConfigurationImport", mybatisConfigurationImport);
        return map;
    }

    /**
     * 生成项目结构
     *
     * @param vo                 vo
     * @param databaseProperties databaseProperties
     * @author tangyh
     * @date 2022/6/14 9:07 PM
     * @create [2022/6/14 9:07 PM ] [tangyh] [初始创建]
     * @update [2022/6/14 9:07 PM ] [tangyh] [变更描述]
     */
    public static void generator(ProjectGeneratorVO vo, DatabaseProperties databaseProperties) {
        String serviceName = vo.getServiceName();
        String serviceNameUpper = StrUtil.upperFirst(serviceName);

        Map<String, Object> objectMap = buildObjectMap(vo, databaseProperties, serviceName, serviceNameUpper);

        ProjectTypeEnum type = vo.getType();
        String outputDir = vo.getOutputDir();
        String projectPrefix = vo.getProjectPrefix();
        String parent = vo.getParent();
        String parentPath = StrUtil.replace(parent, StrPool.DOT, File.separator);

        // 服务 lamp-base
        String service = projectPrefix + StrUtil.DASHED + serviceName;

        List<String> moduleList = TYPE_MODULE_MAP.get(type);
        for (String moduleName : moduleList) {
            // lamp-base-entity
            String module = service + StrUtil.DASHED + moduleName;
            String modulePath = Paths.get(outputDir, service, module).toString();

            // 创建 maven 结构
            mkMaven(modulePath);
            // 创建 基础包
            mkBasePackage(modulePath, parentPath);
            // 生成 pom.xml
            writePom(objectMap, StrUtil.format(POM_FORMAT, moduleName), modulePath);
        }

        // 服务根 pom
        writer(objectMap, StrUtil.format(POM_FORMAT, ROOT), Paths.get(outputDir, service, POM_NAME).toString());

        // server java + yml
        if (ProjectTypeEnum.CLOUD.eq(type.getCode())) {
            String module = service + StrUtil.DASHED + SERVER_SERVICE_SUFFIX;
            String modulePath = Paths.get(outputDir, service, module).toString();
            String javaPath = Paths.get(modulePath, SRC_MAIN_JAVA).toString();
            String resourcePath = Paths.get(modulePath, SRC_MAIN_RESOURCE).toString();
            String javaParentPath = Paths.get(javaPath, parentPath).toString();

            // java
            writer(objectMap, StrUtil.format(JAVA_FORMAT, RUN_APPLICATION_SUFFIX), Paths.get(javaParentPath, serviceNameUpper + RUN_APPLICATION_SUFFIX).toString());
            String configPath = Paths.get(javaParentPath, vo.getModuleName(), "config").toString();
            writer(objectMap, StrUtil.format(JAVA_FORMAT, WEB_CONFIGURATION_SUFFIX), Paths.get(configPath, serviceNameUpper + WEB_CONFIGURATION_SUFFIX).toString());
            writer(objectMap, StrUtil.format(JAVA_FORMAT, EXCEPTION_CONFIGURATION_SUFFIX), Paths.get(configPath, serviceNameUpper + EXCEPTION_CONFIGURATION_SUFFIX).toString());

            // resources
            writer(objectMap, StrUtil.format(RESOURCE_YML_FORMAT, BOOTSTRAP_SUFFIX), Paths.get(resourcePath, BOOTSTRAP_SUFFIX).toString());
            writer(objectMap, StrUtil.format(RESOURCE_YML_FORMAT, BOOTSTRAP_DEV_SUFFIX), Paths.get(resourcePath, BOOTSTRAP_DEV_SUFFIX).toString());
            writer(objectMap, StrUtil.format(RESOURCE_YML_FORMAT, APPLICATION_SUFFIX), Paths.get(resourcePath, APPLICATION_SUFFIX).toString());
            writer(objectMap, StrUtil.format(RESOURCE_XML_FORMAT, LOGBACK_SPRING_SUFFIX), Paths.get(resourcePath, LOGBACK_SPRING_SUFFIX).toString());
            writer(objectMap, StrUtil.format(RESOURCE_XML_FORMAT, LOGBACK_SPRING_DEV_SUFFIX), Paths.get(resourcePath, LOGBACK_SPRING_DEV_SUFFIX).toString());
            String gateway = vo.getProjectPrefix() + StrPool.DASH + GATEWAY_SERVER_SUFFIX;
            writer(objectMap, StrUtil.format(RESOURCE_YML_FORMAT, GATEWAY_SERVER_SUFFIX), Paths.get(resourcePath, gateway).toString());
        } else {
            // 增量追加 server pom文件
            String dependencyStr = StrUtil.format(
                    """
                                    <dependency>
                                        <groupId>{}</groupId>
                                        <artifactId>{}-{}-controller</artifactId>
                                        <version>\\${{}-project.version}</version>
                                    </dependency>
                            """,
                    vo.getGroupId(), projectPrefix, vo.getServiceName(), projectPrefix);
            Map<String, String> map = MapUtil.of("server.pom.xml", dependencyStr);
            // 项目 lamp-boot-server/pom.xml 的存放位置
            String zipOutputFile = Paths.get(outputDir, StrUtil.format("{}-boot-server/pom.xml", projectPrefix)).toString();
            FileInsertUtil.of(zipOutputFile, map).writeFile();
            log.info("成功覆盖文件={}, 需要执行mvn clean package后,重新启动项目才生效", zipOutputFile);

            // 增量追加 application.yml 文件
            String swaggerStr = StrUtil.format("{}:\n" +
                    "        title: {}\n" +
                    "        base-package: {}", serviceName, vo.getDescription(), vo.getParent() + StrPool.DOT + vo.getModuleName());
            String pathStr = StrUtil.format("{}:\n" +
                    "        base-path: /api/base", serviceName);
            Map<String, String> applicationMap = MapUtil.newHashMap();
            applicationMap.put("docket.swagger", swaggerStr);

            String applicationOutFile = Paths.get(outputDir, StrUtil.format("{}-boot-server/{}/application.yml", projectPrefix, SRC_MAIN_RESOURCE)).toString();
            FileInsertUtil.of(applicationOutFile, "    ", applicationMap).writeFile();

            applicationMap = MapUtil.newHashMap();
            pathStr = StrUtil.format(
                    """
                                {}:
                                    base-path: /api/base
                            """,
                    serviceName);
            applicationMap.put("docket.path", pathStr);
            FileInsertUtil.of(applicationOutFile, "      ", applicationMap).writeFile();
            log.info("成功覆盖文件={}", applicationOutFile);
        }

        // 增量追加 根pom文件
        String moduleStr = StrUtil.format("<module>{}-{}</module>", projectPrefix, serviceName);
        Map<String, String> map = MapUtil.of("root.pom.xml", moduleStr);
        // 项目根pom.xml的存放位置
        String zipOutputFile = Paths.get(outputDir, "pom.xml").toString();
        FileInsertUtil.of(zipOutputFile, map).writeFile();
        log.info("成功覆盖文件={}", zipOutputFile);
    }

    private static Map<String, Object> buildObjectMap(ProjectGeneratorVO vo, DatabaseProperties databaseProperties,
                                                      String serviceName, String serviceNameUpper) {
        Map<String, Object> objectMap = MapUtil.newHashMap();
        objectMap.put("pg", vo);
        objectMap.put("moduleName", vo.getModuleName());
        objectMap.put("serviceName", serviceName);
        objectMap.put("serviceNameUpper", serviceNameUpper);
        objectMap.put("projectPrefix", vo.getProjectPrefix());
        objectMap.put("datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT)));
        objectMap.put("projectTypeCloud", ProjectTypeEnum.CLOUD);
        objectMap.put("MULTI_TENANT_TYPE_NONE", MultiTenantType.NONE);
        objectMap.put("MULTI_TENANT_TYPE_DATASOURCE", MultiTenantType.DATASOURCE);
        objectMap.put("MULTI_TENANT_TYPE_DATASOURCE_COLUMN", MultiTenantType.DATASOURCE_COLUMN);
        objectMap.put("MULTI_TENANT_TYPE_COLUMN", MultiTenantType.COLUMN);
        objectMap.put("multiTenantType", databaseProperties.getMultiTenantType());
        objectMap.putAll(getImportPackages(vo, databaseProperties));
        return objectMap;
    }

    /**
     * 创建 maven 目录文件夹
     *
     * @param modulePath
     */
    private static void mkMaven(String modulePath) {
        for (String maven : MAVEN_PATH) {
            String mavenPath = Paths.get(modulePath, maven).toString();
            File file = new File(mavenPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    private static void mkBasePackage(String modulePath, String parent) {
        String basePackage = Paths.get(modulePath, SRC_MAIN_JAVA, parent).toString();
        File file = new File(basePackage);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * 生成pom
     *
     * @param templatePath
     */
    private static void writePom(Map<String, Object> objectMap, String templatePath, String outputFile) {
        writer(objectMap, templatePath, Paths.get(outputFile, POM_NAME).toString());
    }

    @SneakyThrows
    private static void writer(Map<String, Object> objectMap, String templatePath, String outputFile) {
        File file = new File(outputFile);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Template template = TemplateUtils.getTemplate(templatePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            template.process(objectMap, new OutputStreamWriter(fileOutputStream, GenCodeConstant.UTF8));
            log.info("成功生成={}", outputFile);
        }
    }

    @SneakyThrows
    public static DownloadVO download(ProjectGeneratorVO vo, DatabaseProperties databaseProperties) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        String serviceName = vo.getServiceName();
        String serviceNameUpper = StrUtil.upperFirst(serviceName);

        Map<String, Object> objectMap = buildObjectMap(vo, databaseProperties, serviceName, serviceNameUpper);

        ProjectTypeEnum type = vo.getType();
        String outputDir = "";
        String projectPrefix = vo.getProjectPrefix();
        String parent = vo.getParent();
        String parentPath = StrUtil.replace(parent, StrPool.DOT, File.separator);

        // 服务 lamp-base
        String service = projectPrefix + StrUtil.DASHED + serviceName;

        List<String> moduleList = TYPE_MODULE_MAP.get(type);
        for (String moduleName : moduleList) {
            // lamp-base-entity
            String module = service + StrUtil.DASHED + moduleName;
            String modulePath = Paths.get(outputDir, service, module).toString();

            // 创建 maven 结构
            for (String maven : MAVEN_PATH) {
                String mavenPath = Paths.get(modulePath, maven).toString();
                writeDir(zip, mavenPath);
            }

            // 创建 基础包
            String basePackage = Paths.get(modulePath, SRC_MAIN_JAVA, StrUtil.replace(parent, StrUtil.DOT, File.separator)).toString();
            writeDir(zip, basePackage);

            // 生成 pom.xml
            writeZip(objectMap, zip, StrUtil.format(POM_FORMAT, moduleName), Paths.get(outputDir, modulePath, POM_NAME).toString());
        }

        // 服务根 pom
        writeZip(objectMap, zip, StrUtil.format(POM_FORMAT, ROOT), Paths.get(outputDir, service, POM_NAME).toString());

        StringBuffer tips = new StringBuffer();
        tips.append("0. 请自行将下载并解压后的文件夹复制到项目中 \n");
        // server java + yml
        if (ProjectTypeEnum.CLOUD.eq(type.getCode())) {
            String module = service + StrUtil.DASHED + SERVER_SERVICE_SUFFIX;
            String modulePath = Paths.get(outputDir, service, module).toString();
            String javaPath = Paths.get(modulePath, SRC_MAIN_JAVA).toString();
            String resourcePath = Paths.get(modulePath, SRC_MAIN_RESOURCE).toString();
            String javaParentPath = Paths.get(javaPath, parentPath).toString();

            // java
            writeZip(objectMap, zip, StrUtil.format(JAVA_FORMAT, RUN_APPLICATION_SUFFIX), Paths.get(javaParentPath, serviceNameUpper + RUN_APPLICATION_SUFFIX).toString());
            String configPath = Paths.get(javaParentPath, vo.getModuleName(), "config").toString();
            writeZip(objectMap, zip, StrUtil.format(JAVA_FORMAT, WEB_CONFIGURATION_SUFFIX), Paths.get(configPath, serviceNameUpper + WEB_CONFIGURATION_SUFFIX).toString());
            writeZip(objectMap, zip, StrUtil.format(JAVA_FORMAT, EXCEPTION_CONFIGURATION_SUFFIX), Paths.get(configPath, serviceNameUpper + EXCEPTION_CONFIGURATION_SUFFIX).toString());

            // resources
            writeZip(objectMap, zip, StrUtil.format(RESOURCE_YML_FORMAT, BOOTSTRAP_SUFFIX), Paths.get(resourcePath, BOOTSTRAP_SUFFIX).toString());
            writeZip(objectMap, zip, StrUtil.format(RESOURCE_YML_FORMAT, BOOTSTRAP_DEV_SUFFIX), Paths.get(resourcePath, BOOTSTRAP_DEV_SUFFIX).toString());
            writeZip(objectMap, zip, StrUtil.format(RESOURCE_YML_FORMAT, APPLICATION_SUFFIX), Paths.get(resourcePath, APPLICATION_SUFFIX).toString());
            writeZip(objectMap, zip, StrUtil.format(RESOURCE_XML_FORMAT, LOGBACK_SPRING_SUFFIX), Paths.get(resourcePath, LOGBACK_SPRING_SUFFIX).toString());
            writeZip(objectMap, zip, StrUtil.format(RESOURCE_XML_FORMAT, LOGBACK_SPRING_DEV_SUFFIX), Paths.get(resourcePath, LOGBACK_SPRING_DEV_SUFFIX).toString());
            String gateway = vo.getProjectPrefix() + StrPool.DASH + GATEWAY_SERVER_SUFFIX;
            writeZip(objectMap, zip, StrUtil.format(RESOURCE_YML_FORMAT, GATEWAY_SERVER_SUFFIX), Paths.get(resourcePath, gateway).toString());
        } else {
            // 增量追加 server pom文件

            String dependencyStr = StrUtil.format(
                    """
                                    <dependency>
                                        <groupId>{}</groupId>
                                        <artifactId>{}-{}-controller</artifactId>
                                        <version>${{}-project.version}</version>
                                    </dependency>
                            """
                    , vo.getGroupId(), projectPrefix, vo.getServiceName(), projectPrefix);

            tips.append("1. 请在 lamp-boot-server/pom.xml 中加入以下 代码：\n");
            tips.append(dependencyStr);

            // 增量追加 application.yml 文件
            String swaggerStr = StrUtil.format(
                    """
                                - group: '{}'
                                  displayName: '{}'
                                  paths-to-match: '/**'
                                  packages-to-scan:
                                    - {}
                            """
                    , serviceName, vo.getDescription(), vo.getParent() + StrPool.DOT + vo.getModuleName());
            String pathStr = StrUtil.format(
                    """
                                {}:
                                    base-path: /api/base
                            """
                    , serviceName);

            tips.append("\n 2. 请在 application.yml 中加入以下 代码：\n");
            tips.append(swaggerStr);
            tips.append("\n 3. 请在 application.yml 中加入以下 代码：\n");
            tips.append(pathStr);

        }

        // 增量追加 根pom文件
        String moduleStr = StrUtil.format("<module>{}-{}</module>", projectPrefix, serviceName);
        tips.append("\n 4. 请在 pom.xml 中加入以下 代码：\n");
        tips.append(moduleStr);

        tips.append("\n 5. 若不知道如何执行上面步骤，请本地启动后直接生成，程序可以直接覆盖文件。无需任何手动操作\n");

        try (StringWriter sw = new StringWriter()) {
            sw.append(tips);
            zip.putNextEntry(new ZipEntry("友情提示.md"));
            IOUtils.write(sw.toString(), zip, StrPool.UTF8);
        } catch (IOException e) {
            log.info("代码生成异常, 出错原因可能是的表结构没有按照规范编写，导致模板解析出错！", e);
        } finally {
            try {
                zip.flush();
                zip.closeEntry();
            } catch (IOException ee) {
                log.error("ee=", ee);
            }
        }

        IoUtil.close(zip);
        String fileName = service + ".zip";
        return DownloadVO.builder()
                .fileName(fileName)
                .data(outputStream.toByteArray())
                .build();
    }

    @SneakyThrows
    private static void writeReplaceFile(ZipOutputStream zip, String content, String outputFile) {
        zip.putNextEntry(new ZipEntry(outputFile));
        IOUtils.write(content, zip, StrPool.UTF8);
        zip.flush();
        zip.closeEntry();
    }

    @SneakyThrows
    private static void writeDir(ZipOutputStream zip, String outputFile) {
        outputFile += "/";
        zip.putNextEntry(new ZipEntry(outputFile));
        zip.flush();
        zip.closeEntry();
    }

    private static void writeZip(Map<String, Object> objectMap, ZipOutputStream zip, String templatePath, String outputFile) {
        try (StringWriter sw = new StringWriter()) {
            Template tpl = TemplateUtils.getTemplate(templatePath);
            tpl.process(objectMap, sw);
            zip.putNextEntry(new ZipEntry(outputFile));
            IOUtils.write(sw.toString(), zip, StrPool.UTF8);
        } catch (TemplateException | IOException e) {
            log.info("代码生成异常, 出错原因可能是的表结构没有按照规范编写，导致模板解析出错！", e);
        } finally {
            try {
                zip.flush();
                zip.closeEntry();
            } catch (IOException ee) {
                log.error("ee=", ee);
            }
        }
    }
}
