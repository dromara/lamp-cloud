package top.tangyh.lamp.generator.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.generator.config.ControllerConfig;
import top.tangyh.lamp.generator.config.EntityConfig;
import top.tangyh.lamp.generator.config.FileOverrideStrategy;
import top.tangyh.lamp.generator.config.GeneratorConfig;
import top.tangyh.lamp.generator.config.ManagerConfig;
import top.tangyh.lamp.generator.config.MapperConfig;
import top.tangyh.lamp.generator.config.PackageInfoConfig;
import top.tangyh.lamp.generator.config.ServiceConfig;
import top.tangyh.lamp.generator.entity.DefGenTable;
import top.tangyh.lamp.generator.enumeration.FileOverrideStrategyEnum;
import top.tangyh.lamp.generator.enumeration.TemplateEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

import static top.tangyh.lamp.generator.utils.inner.PackageUtils.getName;

/**
 * 输出文件工具类
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/4/8 12:37 PM
 * @create [2022/4/8 12:37 PM ] [tangyh] [初始创建]
 */
public class OutputFileUtils {

    /**
     * 获取压缩到zip中的地址
     *
     * @param generatorConfig generatorConfig
     * @param genTable        genTable
     * @param subTable        subTable
     * @param templatePath    templatePath
     * @param enumName        enumName
     * @param template        template
     * @return java.lang.String
     * @author tangyh
     * @date 2022/4/8 12:45 PM
     * @create [2022/4/8 12:45 PM ] [tangyh] [初始创建]
     */
    public static String getZipOutputFile(GeneratorConfig generatorConfig, DefGenTable genTable, DefGenTable subTable, String templatePath, String enumName, TemplateEnum template) {
        if (TemplateEnum.BACKEND.eq(template)) {
            return getOutputFile(generatorConfig, genTable, templatePath, false, enumName);
        } else {
            return getFrontOutputFile(genTable, subTable, templatePath, false);
        }
    }

    /**
     * 文件生成到本地的地址
     *
     * @param generatorConfig generatorConfig
     * @param genTable        genTable
     * @param subTable        subTable
     * @param templatePath    templatePath
     * @param enumName        enumName
     * @param template        template
     * @return java.lang.String
     * @author tangyh
     * @date 2022/4/8 12:45 PM
     * @create [2022/4/8 12:45 PM ] [tangyh] [初始创建]
     */
    public static String getOutputFile(GeneratorConfig generatorConfig, DefGenTable genTable, DefGenTable subTable, String templatePath, String enumName, TemplateEnum template) {
        if (TemplateEnum.BACKEND.eq(template)) {
            return getOutputFile(generatorConfig, genTable, templatePath, true, enumName);
        } else {
            return getFrontOutputFile(genTable, subTable, templatePath, true);
        }
    }

    /**
     * 获取 lamp-web-pro 项目的文件生成路径
     *
     * @param genTable     表配置
     * @param subTable     从表配置
     * @param templatePath 生成代码的模板路径
     * @param isAbsolute   是否绝对地址
     * @return
     */
    private static String getFrontOutputFile(DefGenTable genTable, DefGenTable subTable, String templatePath, boolean isAbsolute) {
        String outputDir = genTable.getFrontOutputDir();
        String plusApplicationName = genTable.getPlusApplicationName();
        String plusModuleName = genTable.getPlusModuleName();
        String entityName = StrUtil.lowerFirst(genTable.getEntityName());

        String frontOutputFile;
        if (templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_API)) {
            frontOutputFile = StrUtil.format("src/api/{}/{}/{}.ts", plusApplicationName, plusModuleName, entityName);
        } else if (templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_MODEL)) {
            frontOutputFile = StrUtil.format("src/api/{}/{}/model/{}Model.ts", plusApplicationName, plusModuleName, entityName);
        } else if (templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_LANG_EN)) {
            frontOutputFile = StrUtil.format("src/locales/lang/en/{}/{}/{}.ts", plusApplicationName, plusModuleName, entityName);
        } else if (templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_LANG_ZH)) {
            frontOutputFile = StrUtil.format("src/locales/lang/zh-CN/{}/{}/{}.ts", plusApplicationName, plusModuleName, entityName);
        } else if (templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_DATA)) {
            frontOutputFile = StrUtil.format("src/views/{}/{}/{}/{}.data.tsx", plusApplicationName, plusModuleName, entityName, entityName);
        } else if (templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_INDEX) || templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_INDEX) || templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_TREE_INDEX)) {
            frontOutputFile = StrUtil.format("src/views/{}/{}/{}/index.vue", plusApplicationName, plusModuleName, entityName);
        } else if (templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_EDIT) || templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_JUMP_EDIT) || templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_EDIT) || templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_JUMP_EDIT) || templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_TREE_EDIT)) {
            frontOutputFile = StrUtil.format("src/views/{}/{}/{}/Edit.vue", plusApplicationName, plusModuleName, entityName);
        } else if (templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_SUB_INDEX)) {
            String subEntityName = StrUtil.lowerFirst(subTable.getEntityName());
            frontOutputFile = StrUtil.format("src/views/{}/{}/{}/{}/index.vue", plusApplicationName, plusModuleName, entityName, subEntityName);
        } else if (templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_SUB_DATA)) {
            String subEntityName = StrUtil.lowerFirst(subTable.getEntityName());
            frontOutputFile = StrUtil.format("src/views/{}/{}/{}/{}/{}.data.tsx", plusApplicationName, plusModuleName, entityName, subEntityName, subEntityName);
        } else if (templatePath.equals(GenCodeConstant.TEMPLATE_WEB_PRO_TREE_TREE)) {
            frontOutputFile = StrUtil.format("src/views/{}/{}/{}/Tree.vue", plusApplicationName, plusModuleName, entityName);
        } else {
            return outputDir;
        }
        if (isAbsolute) {
            frontOutputFile = outputDir + File.separator + frontOutputFile;
        }
        return Paths.get(frontOutputFile).toString();
    }

    private static String getModular(String projectPrefix, String serviceName, String subModularSuffixName) {
        return projectPrefix + StrUtil.DASHED + serviceName + StrUtil.DASHED + subModularSuffixName;
    }

    /**
     * 获取 lamp-cloud / lamp-boot 项目的文件生成路径
     *
     * @param generatorConfig 全局默认配置
     * @param genTable        表配置
     * @param templatePath    生成代码的模板路径
     * @param isAbsolute      是否绝对地址
     * @param enumName        enumName
     * @return java.lang.String
     * @author tangyh
     * @date 2022/4/8 12:47 PM
     * @create [2022/4/8 12:47 PM ] [tangyh] [初始创建]
     */
    private static String getOutputFile(GeneratorConfig generatorConfig, DefGenTable genTable, String templatePath, boolean isAbsolute, String enumName) {
        MapperConfig mapperConfig = generatorConfig.getMapperConfig();
        ManagerConfig managerConfig = generatorConfig.getManagerConfig();
        ServiceConfig serviceConfig = generatorConfig.getServiceConfig();
        ControllerConfig controllerConfig = generatorConfig.getControllerConfig();
        EntityConfig entityConfig = generatorConfig.getEntityConfig();
        PackageInfoConfig packageConfig = generatorConfig.getPackageInfoConfig();
        String outputDir = isAbsolute ? genTable.getOutputDir() : StrPool.EMPTY;
        // lamp
        String projectPrefix = generatorConfig.getProjectPrefix();
        // msg
        String serviceName = genTable.getServiceName();
        // controller biz entity api
        String subModularSuffixName;

        // 服务
        String service = projectPrefix + StrUtil.DASHED + serviceName;

        // src/main/java  src/main/resources
        String mavenPath = GenCodeConstant.SRC_MAIN_JAVA;
        // msg
        String modularName = genTable.getModuleName();
        // top/tangyh/lamp/msg
        String basePackagePath = genTable.getParent() + File.separator + modularName;
        // 分层  entity dao service controller vo
//        String layeredName = "";
        // common org base
        String childPackageName = genTable.getChildPackageName() == null ? StrPool.EMPTY : genTable.getChildPackageName();
        // 文件名
        String fileName;

        // 模块
        String modular;
        String packagePath;
        switch (templatePath) {
            // 枚举
            case GenCodeConstant.TEMPLATE_ENUM:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                // lamp-base-entity
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getEnumeration(), childPackageName);
                fileName = enumName + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_PAGE_QUERY:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getPageQuery(), childPackageName);
                fileName = getName(genTable.getEntityName(), entityConfig.getFormatPageQueryFileName(), GenCodeConstant.PAGE_QUERY) + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_SAVE_VO:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getSaveVo(), childPackageName);
                fileName = getName(genTable.getEntityName(), entityConfig.getFormatSaveVoFileName(), GenCodeConstant.SAVE_VO) + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_UPDATE_VO:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getUpdateVo(), childPackageName);
                fileName = getName(genTable.getEntityName(), entityConfig.getFormatUpdateVoFileName(), GenCodeConstant.UPDATE_VO) + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_RESULT_VO:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getResultVo(), childPackageName);
                fileName = getName(genTable.getEntityName(), entityConfig.getFormatResultVoFileName(), GenCodeConstant.RESULT_VO) + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_ENTITY_JAVA:
                subModularSuffixName = GenCodeConstant.ENTITY_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getEntity(), childPackageName);
                fileName = genTable.getEntityName() + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_MAPPER:
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getMapper(), childPackageName);
                fileName = getName(genTable.getEntityName(), mapperConfig.getFormatMapperFileName(), GenCodeConstant.MAPPER) + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_XML:
                mavenPath = GenCodeConstant.SRC_MAIN_RESOURCE;
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = "mapper_" + modularName + File.separator + "base";
                fileName = getName(genTable.getEntityName(), mapperConfig.getFormatXmlFileName(), GenCodeConstant.MAPPER) + GenCodeConstant.XML_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_MANAGER:
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getManager(), childPackageName);
                fileName = getName(genTable.getEntityName(), managerConfig.getFormatManagerFileName(), GenCodeConstant.MANAGER) + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_MANAGER_IMPL:
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                if (StrUtil.contains(packageConfig.getManagerImpl(), StrPool.BRACE)) {
                    packagePath = buildPath(basePackagePath, StrUtil.format(packageConfig.getManagerImpl(), childPackageName + StrPool.DOT));
                } else {
                    packagePath = buildPath(basePackagePath, packageConfig.getManagerImpl(), childPackageName);
                }
                fileName = getName(genTable.getEntityName(), managerConfig.getFormatManagerImplFileName(), GenCodeConstant.MANAGER_IMPL) + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_SERVICE:
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getService(), childPackageName);
                fileName = getName(genTable.getEntityName(), serviceConfig.getFormatServiceFileName(), GenCodeConstant.SERVICE) + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_SERVICE_IMPL:
                subModularSuffixName = GenCodeConstant.BIZ_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                if (StrUtil.contains(packageConfig.getServiceImpl(), StrPool.BRACE)) {
                    packagePath = buildPath(basePackagePath, StrUtil.format(packageConfig.getServiceImpl(), childPackageName + StrPool.DOT));
                } else {
                    packagePath = buildPath(basePackagePath, packageConfig.getServiceImpl(), childPackageName);
                }
                fileName = getName(genTable.getEntityName(), serviceConfig.getFormatServiceImplFileName(), GenCodeConstant.SERVICE_IMPL) + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_CONTROLLER:
                subModularSuffixName = GenCodeConstant.CONTROLLER_SERVICE_SUFFIX;
                // 模块
                modular = getModular(projectPrefix, serviceName, subModularSuffixName);
                packagePath = buildPath(basePackagePath, packageConfig.getController(), childPackageName);
                fileName = getName(genTable.getEntityName(), controllerConfig.getFormatFileName(), GenCodeConstant.CONTROLLER) + GenCodeConstant.JAVA_SUFFIX;
                return buildPath(outputDir, service, modular, mavenPath, packagePath, fileName);
            case GenCodeConstant.TEMPLATE_SQL:
                return buildPath(outputDir, service, "初始化SQL_" + genTable.getName() + GenCodeConstant.SQL_SUFFIX);
            default:
                break;
        }
        return outputDir;
    }

    private static String buildPath(String first, String... more) {
        String firstPath = StrUtil.replace(first, StrUtil.DOT, File.separator);
        String[] paths = new String[more.length];
        for (int i = 0; i < more.length; i++) {
            if (StrUtil.endWithAny(more[i], GenCodeConstant.JAVA_SUFFIX, GenCodeConstant.XML_SUFFIX, GenCodeConstant.SQL_SUFFIX)) {
                paths[i] = more[i];
            } else {
                paths[i] = StrUtil.replace(more[i], StrUtil.DOT, File.separator);
            }
            paths[i] = paths[i] == null ? StrPool.EMPTY : paths[i];
        }
        return Paths.get(firstPath, paths).toString();
    }

    public static FileOverrideStrategyEnum getFileOverride(GeneratorConfig generatorConfig, Map<String, FileOverrideStrategyEnum> fileOverrideConfig, String templatePath, String enumName) {
        FileOverrideStrategy defStrategy = generatorConfig.getFileOverrideStrategy();
        if (CollUtil.isNotEmpty(fileOverrideConfig)) {
            FileOverrideStrategyEnum fileOverrideStrategy = fileOverrideConfig.get(templatePath);
            if (fileOverrideStrategy != null) {
                if (GenCodeConstant.TEMPLATE_ENUM.equals(templatePath)) {
                    Map<String, Class<?>> constantsPackage = generatorConfig.getConstantsPackage();
                    return constantsPackage.containsKey(enumName) ? FileOverrideStrategyEnum.IGNORE : defStrategy.getEntityFileOverride();
                }
                return fileOverrideStrategy;
            }
        }

        return switch (templatePath) {
            case GenCodeConstant.TEMPLATE_SQL -> defStrategy.getSqlFileOverride();
            case GenCodeConstant.TEMPLATE_CONTROLLER -> defStrategy.getControllerFileOverride();
            case GenCodeConstant.TEMPLATE_SERVICE, GenCodeConstant.TEMPLATE_SERVICE_IMPL ->
                    CollUtil.isNotEmpty(fileOverrideConfig) && fileOverrideConfig.get(GenCodeConstant.TEMPLATE_SERVICE) != null ?
                            fileOverrideConfig.get(GenCodeConstant.TEMPLATE_SERVICE) : defStrategy.getServiceFileOverride();
            case GenCodeConstant.TEMPLATE_MANAGER, GenCodeConstant.TEMPLATE_MANAGER_IMPL ->
                    CollUtil.isNotEmpty(fileOverrideConfig) && fileOverrideConfig.get(GenCodeConstant.TEMPLATE_MANAGER) != null ?
                            fileOverrideConfig.get(GenCodeConstant.TEMPLATE_MANAGER) : defStrategy.getManagerFileOverride();
            case GenCodeConstant.TEMPLATE_MAPPER -> defStrategy.getMapperFileOverride();
            case GenCodeConstant.TEMPLATE_XML -> defStrategy.getXmlFileOverride();
            case GenCodeConstant.TEMPLATE_ENTITY_JAVA, GenCodeConstant.TEMPLATE_SAVE_VO, GenCodeConstant.TEMPLATE_UPDATE_VO,
                    GenCodeConstant.TEMPLATE_PAGE_QUERY, GenCodeConstant.TEMPLATE_RESULT_VO ->
                    defStrategy.getEntityFileOverride();
            case GenCodeConstant.TEMPLATE_ENUM -> {
                Map<String, Class<?>> constantsPackage = generatorConfig.getConstantsPackage();
                yield constantsPackage.containsKey(enumName) ? FileOverrideStrategyEnum.IGNORE : defStrategy.getEntityFileOverride();
            }
            case GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_API, GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_MODEL ->
                    defStrategy.getApiModelFileOverride();
            case GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_LANG_EN, GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_LANG_ZH ->
                    defStrategy.getLangFileOverride();
            case GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_EDIT, GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_JUMP_EDIT,
                    GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_SUB_INDEX, GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_SUB_DATA,
                    GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_EDIT, GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_JUMP_EDIT,
                    GenCodeConstant.TEMPLATE_WEB_PRO_TREE_EDIT, GenCodeConstant.TEMPLATE_WEB_PRO_TREE_TREE,
                    GenCodeConstant.TEMPLATE_WEB_PRO_TREE_INDEX ->
                    CollUtil.isNotEmpty(fileOverrideConfig) && fileOverrideConfig.get(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_EDIT) != null ?
                            fileOverrideConfig.get(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_EDIT) :
                            defStrategy.getIndexEditTreeFileOverride();
            case GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_INDEX, GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_INDEX ->
                    CollUtil.isNotEmpty(fileOverrideConfig) && fileOverrideConfig.get(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_INDEX) != null ?
                            fileOverrideConfig.get(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_INDEX) :
                            defStrategy.getIndexEditTreeFileOverride();
            case GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_DATA -> defStrategy.getDataFileOverride();
            default -> FileOverrideStrategyEnum.OVERRIDE;
        };
    }
}
