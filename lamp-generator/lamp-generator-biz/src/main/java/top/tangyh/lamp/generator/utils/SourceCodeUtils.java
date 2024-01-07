package top.tangyh.lamp.generator.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baidu.fsg.uid.UidGenerator;
import com.baomidou.mybatisplus.annotation.DbType;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.database.properties.DatabaseProperties;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.DateUtils;
import top.tangyh.basic.utils.StrHelper;
import top.tangyh.lamp.common.constant.DefValConstants;
import top.tangyh.lamp.generator.config.ControllerConfig;
import top.tangyh.lamp.generator.config.GeneratorConfig;
import top.tangyh.lamp.generator.entity.DefGenTable;
import top.tangyh.lamp.generator.entity.DefGenTableColumn;
import top.tangyh.lamp.generator.enumeration.EntitySuperClassEnum;
import top.tangyh.lamp.generator.enumeration.PopupTypeEnum;
import top.tangyh.lamp.generator.enumeration.SuperClassEnum;
import top.tangyh.lamp.generator.enumeration.TplEnum;
import top.tangyh.lamp.generator.rules.echo.EchoDict;
import top.tangyh.lamp.generator.rules.echo.EchoType;
import top.tangyh.lamp.generator.utils.inner.CommentUtils;
import top.tangyh.lamp.generator.utils.inner.PackageUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 源码生成 工具类
 *
 * @author zuihou
 * @date 2022/3/14 19:40
 */
public class SourceCodeUtils {

    /**
     * 组装模板参数
     *
     * @param generatorConfig    默认配置
     * @param databaseProperties 数据元配置
     * @param genTable           表信息
     * @param allFieldList       所有字段
     * @param uidGenerator       uidGenerator
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author tangyh
     * @date 2022/4/6 8:55 PM
     * @create [2022/4/6 8:55 PM ] [tangyh] [初始创建]
     */
    public static Map<String, Object> getObjectMap(GeneratorConfig generatorConfig, DatabaseProperties databaseProperties, UidGenerator uidGenerator,
                                                   Map<String, Object> subObjectMap, DefGenTable genTable, List<DefGenTableColumn> allFieldList,
                                                   DbType dbType) {
        Map<String, Object> map = new HashMap<>();
        if (CollUtil.isNotEmpty(subObjectMap)) {
            map.put("sub", subObjectMap);
        } else {
            map.put("sub", Collections.emptyMap());
        }
        map.put("config", generatorConfig);
        map.put("controllerConfig", generatorConfig.getControllerConfig());
        map.put("entityConfig", generatorConfig.getEntityConfig());
        map.put("serviceConfig", generatorConfig.getServiceConfig());
        map.put("managerConfig", generatorConfig.getManagerConfig());
        map.put("mapperConfig", generatorConfig.getMapperConfig());
        map.put("table", genTable);
        map.put("datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT)));
        map.put("dbType", dbType.getDb());
        map.put("oracle", DbType.ORACLE.getDb());
        // 是否树型结构
        map.put("isTreeEntity", EntitySuperClassEnum.TREE_ENTITY.eq(genTable.getEntitySuperClass()));

        // 设置不同类型的字段，并返回普通字段
        List<DefGenTableColumn> allFields = getDefGenTableColumns(databaseProperties, genTable, allFieldList, map);

        map.put("projectPrefix", generatorConfig.getProjectPrefix());
        map.put("author", genTable.getAuthor());
        map.put("parent", genTable.getParent());
        map.put("utilParent", genTable.getParent());
        map.put("moduleName", genTable.getModuleName());

        // 导包信息
        Map<String, Object> packageMap = PackageUtils.getPackage(genTable, generatorConfig);
        map.put("package", packageMap);

        // controller 常用参数
        map.putAll(controllerMap(genTable, generatorConfig));
        // service 常用参数
        map.putAll(serviceMap(genTable, generatorConfig));
        // 父类
        map.putAll(PackageUtils.getSuperClassPackage(genTable));
        // 业务类 名称和完整包路径
        map.putAll(PackageUtils.getMvcPackage(genTable, generatorConfig, packageMap));
        // 业务类 需要导入的包
        map.putAll(PackageUtils.getImportPackages(dbType, genTable, generatorConfig, allFields, map));
        // 常量
        map.putAll(getConstant());
        map.putAll(getSqlParams(genTable, uidGenerator));

        Set<EchoDict> dictList = new HashSet<>();
        allFields.forEach(field -> {
            EchoDict echoDict = initEchoDictSql(field);
            if (echoDict != null) {
                dictList.add(echoDict);
            }
        });
        map.put("dictList", dictList);

        return map;
    }

    /**
     * 解析注释中的字典列表
     * [x-y a-b]
     *
     * @param field field
     * @return top.tangyh.lamp.generator.rules.echo.EchoDict
     * @author tangyh
     * @date 2022/4/21 10:05 PM
     * @create [2022/4/21 10:05 PM ] [tangyh] [初始创建]
     */
    private static EchoDict initEchoDictSql(DefGenTableColumn field) {
        String echoStr = StrUtil.isNotEmpty(field.getEchoStr()) ? field.getEchoStr() : field.getComment();
        if (StrUtil.isNotEmpty(echoStr)) {
            EchoType echoType = CommentUtils.getEchoType(echoStr);
            if (echoType != null) {

                String fieldComment = StrUtil.isNotEmpty(field.getSwaggerComment()) ? field.getSwaggerComment() : field.getName();
                return CommentUtils.getEchoDict(echoType.getDictTypeField(), fieldComment, field.getComment());
            }
        }
        return null;
    }

    private static List<DefGenTableColumn> getDefGenTableColumns(DatabaseProperties databaseProperties, DefGenTable genTable,
                                                                 List<DefGenTableColumn> allFieldList, Map<String, Object> map) {
        List<DefGenTableColumn> pkFieldList = allFieldList.stream().filter(DefGenTableColumn::getIsPk).toList();
        ArgumentAssert.notEmpty(pkFieldList, "请设置主键id");
        ArgumentAssert.isFalse(pkFieldList.size() > 1, "目前只支持1个主键id, 不支持符合组件");
        map.put("pkField", pkFieldList.get(0));
        List<DefGenTableColumn> commonFields = new ArrayList<>();
        List<DefGenTableColumn> fields = new ArrayList<>();
        allFieldList.forEach(field -> {
            if (genTable.getEntitySuperClass() != null && genTable.getEntitySuperClass().matchSuperEntityColumns(field.getName())) {
                commonFields.add(field);
            } else {
                fields.add(field);
            }
        });

        // 所有字段
        map.put("allFields", allFieldList);
        // 父类含有的字段
        map.put("commonFields", commonFields);
        // 普通字段
        map.put("fields", fields);
        // 租户列
        map.put("tenantIdColumn", databaseProperties.getTenantIdColumn());
        return allFieldList;
    }

    private static Map<String, Object> controllerMap(DefGenTable genTable, GeneratorConfig generatorConfig) {
        ControllerConfig controllerConfig = generatorConfig.getControllerConfig();
        Map<String, Object> map = new HashMap<>();
        if (controllerConfig.getHyphenStyle()) {
            map.put("mappingHyphen", StrHelper.convertToCamelCase(StrUtil.lowerFirst(genTable.getEntityName())));
        }
        return map;
    }

    private static Map<String, Object> serviceMap(DefGenTable genTable, GeneratorConfig generatorConfig) {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    private static Map<String, Object> getConstant() {
        Map<String, Object> map = new HashMap<>();
        map.put("TPL_TREE", TplEnum.TREE);
        map.put("TPL_MAIN_SUB", TplEnum.MAIN_SUB);
        map.put("POPUP_TYPE_MODAL", PopupTypeEnum.MODAL);
        map.put("POPUP_TYPE_DRAWER", PopupTypeEnum.DRAWER);
        map.put("POPUP_TYPE_JUMP", PopupTypeEnum.JUMP);
        map.put("SUPER_CLASS_SUPER_POI_CLASS", SuperClassEnum.SUPER_POI_CLASS);
        return map;
    }

    public static Map<String, Object> getSqlParams(DefGenTable genTable, UidGenerator uidGenerator) {
        Map<String, Object> map = new HashMap<>();
        map.put("applicationId", genTable.getMenuApplicationId());
        map.put("parentMenuId", genTable.getMenuParentId());
        map.put("createdBy", ContextUtil.getUserId());
        map.put("menuId", uidGenerator.getUid());
        map.put("buttonAddId", uidGenerator.getUid());
        map.put("buttonEditId", uidGenerator.getUid());
        map.put("buttonCopyId", uidGenerator.getUid());
        map.put("buttonDeleteId", uidGenerator.getUid());
        map.put("buttonViewId", uidGenerator.getUid());
        map.put("apiPageId", uidGenerator.getUid());
        map.put("apiDetailId", uidGenerator.getUid());
        map.put("apiAddId", uidGenerator.getUid());
        map.put("apiEditId", uidGenerator.getUid());
        map.put("apiDeleteId", uidGenerator.getUid());
        map.put("apiCopyId", uidGenerator.getUid());

        map.put("trMenuId", uidGenerator.getUid());
        map.put("trButtonAddId", uidGenerator.getUid());
        map.put("trButtonEditId", uidGenerator.getUid());
        map.put("trButtonCopyId", uidGenerator.getUid());
        map.put("trButtonDeleteId", uidGenerator.getUid());
        map.put("trButtonViewId", uidGenerator.getUid());
        map.put("uidGenerator", uidGenerator);
        return map;
    }

}
