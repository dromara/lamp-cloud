package top.tangyh.lamp.generator.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.Table;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import top.tangyh.basic.utils.StrHelper;
import top.tangyh.basic.utils.StrPool;

import top.tangyh.lamp.generator.config.EntityConfig;
import top.tangyh.lamp.generator.config.GeneratorConfig;
import top.tangyh.lamp.generator.config.MapperConfig;
import top.tangyh.lamp.generator.config.ServiceConfig;
import top.tangyh.lamp.generator.config.WebProConfig;
import top.tangyh.lamp.generator.converts.ITypeConvert;
import top.tangyh.lamp.generator.converts.TypeConverts;
import top.tangyh.lamp.generator.entity.DefGenTable;
import top.tangyh.lamp.generator.entity.DefGenTableColumn;
import top.tangyh.lamp.generator.enumeration.ComponentEnum;
import top.tangyh.lamp.generator.enumeration.SqlConditionEnum;
import top.tangyh.lamp.generator.enumeration.VxeComponentEnum;
import top.tangyh.lamp.generator.rules.ColumnType;
import top.tangyh.lamp.generator.rules.DbColumnType;
import top.tangyh.lamp.generator.rules.NamingStrategy;
import top.tangyh.lamp.generator.rules.echo.EchoType;
import top.tangyh.lamp.generator.rules.enumeration.EnumType;
import top.tangyh.lamp.generator.type.GenConstants;
import top.tangyh.lamp.generator.utils.inner.CommentUtils;

import java.util.List;
import java.util.Map;

import static top.tangyh.lamp.generator.utils.inner.PackageUtils.getName;

/**
 * 代码生成器 工具类
 *
 * @author zuihou
 * @date 2022年3月3日15:05:17
 */
@Slf4j
public class GenUtils {

    /**
     * 初始化表信息
     */
    public static DefGenTable initTable(GeneratorConfig generatorConfig, Table tableMeta) {
        ServiceConfig serviceConfig = generatorConfig.getServiceConfig();
        MapperConfig mapperConfig = generatorConfig.getMapperConfig();
        EntityConfig entityConfig = generatorConfig.getEntityConfig();
        WebProConfig webProConfig = generatorConfig.getWebProConfig();

        DefGenTable genTable = new DefGenTable();
        genTable.setName(tableMeta.getTableName());
        genTable.setComment(tableMeta.getComment());
        genTable.setSwaggerComment(getSwaggerComment(tableMeta.getComment()));
        genTable.setMenuName(getName(genTable.getSwaggerComment(), webProConfig.getFormatMenuName(), "维护"));
        genTable.setAuthor(generatorConfig.getAuthor());
        genTable.setEntityName(convertClassName(generatorConfig, tableMeta.getTableName()));
        genTable.setTplType(webProConfig.getTpl());
        genTable.setParent(generatorConfig.getPackageInfoConfig().getParent());
        genTable.setPlusApplicationName(StrPool.EMPTY);
        genTable.setPlusModuleName(StrPool.EMPTY);
        genTable.setServiceName(StrPool.EMPTY);
        genTable.setModuleName(StrPool.EMPTY);
        genTable.setChildPackageName(StrPool.EMPTY);
        genTable.setGenType(generatorConfig.getGenType());
        genTable.setOutputDir(generatorConfig.getOutputDir());
        genTable.setFrontOutputDir(generatorConfig.getFrontOutputDir());
        genTable.setEntitySuperClass(entityConfig.getEntitySuperClass());
        genTable.setSuperClass(generatorConfig.getSuperClass());

        genTable.setIsTenantLine(mapperConfig.getColumnAnnotationTablePrefix().stream().anyMatch(tablePrefix -> tableMeta.getTableName().startsWith(tablePrefix)));
        genTable.setIsDs(serviceConfig.getDsTablePrefix().stream().anyMatch(tablePrefix -> tableMeta.getTableName().startsWith(tablePrefix)));
        genTable.setDsValue(StrPool.EMPTY);

        genTable.setIsLombok(entityConfig.getLombok());
        genTable.setIsChain(entityConfig.getChain());
        genTable.setIsColumnConstant(entityConfig.getColumnConstant());

        genTable.setAddShow(true);
        genTable.setCopyShow(true);
        genTable.setEditShow(true);
        genTable.setDeleteShow(true);

        return genTable;
    }

    /**
     * 表名转换成Java类名
     *
     * @param tableName 表名称
     * @return 类名
     */
    public static String convertClassName(GeneratorConfig generatorConfig, String tableName) {
        List<String> tablePrefix = generatorConfig.getTablePrefix();
        if (CollUtil.isEmpty(tablePrefix)) {
            return StrHelper.convertToCamelCase(tableName);
        }

        String text = tableName;
        for (String prefix : tablePrefix) {
            if (StrUtil.isEmpty(prefix)) {
                continue;
            }
            if (tableName.startsWith(prefix)) {
                text = tableName.replaceFirst(prefix, StrUtil.EMPTY);
                break;
            }
        }
        return StrHelper.convertToCamelCase(text);
    }

    /**
     * @param name     列名称
     * @param strategy 策略
     * @param prefix   前缀
     * @param suffix   后缀
     * @return java.lang.String
     * @date 2023/4/16 10:45 PM
     */
    private static String processName(String name, NamingStrategy strategy, List<String> prefix, List<String> suffix) {
        String propertyName = name;
        // 删除前缀
        if (prefix.size() > 0) {
            propertyName = NamingStrategy.removePrefix(propertyName, prefix);
        }
        // 删除后缀
        if (suffix.size() > 0) {
            propertyName = NamingStrategy.removeSuffix(propertyName, suffix);
        }
        if (StringUtils.isBlank(propertyName)) {
            throw new RuntimeException(String.format("%s 的名称转换结果为空，请检查是否配置问题", name));
        }
        // 下划线转驼峰
        if (NamingStrategy.underline_to_camel.equals(strategy)) {
            return NamingStrategy.underlineToCamel(propertyName);
        }
        return propertyName;
    }

    private static String getSwaggerComment(String comment) {
        String swaggerComment = StrUtil.isBlank(comment) ? StrUtil.EMPTY : StrUtil.trim(comment);
        if (swaggerComment.contains(StrPool.SEMICOLON)) {
            swaggerComment = StrUtil.subBefore(swaggerComment, StrPool.SEMICOLON, false);
        }
        swaggerComment = StrUtil.replace(swaggerComment, "\n", " ");
        return swaggerComment;
    }


    /**
     * 初始化列属性字段
     */
    public static DefGenTableColumn initColumnField(GeneratorConfig generatorConfig, DbType dbType, DefGenTable genTable, Column column) {
        EntityConfig entityConfig = generatorConfig.getEntityConfig();
        if (entityConfig.getIgnoreColumns().contains(column.getName())) {
            log.info("已经忽略字段： {}.{} ", genTable.getName(), column.getName());
            return null;
        }

        DefGenTableColumn tableColumn = new DefGenTableColumn();
        String name = column.getName();
        tableColumn.setJavaField(processName(name, entityConfig.getColumnNaming(), generatorConfig.getFieldPrefix(), generatorConfig.getFieldSuffix()));
        tableColumn.setComment(column.getComment());
        tableColumn.setSwaggerComment(getSwaggerComment(column.getComment()));
        // 解析注释中的@Echo注解
        EchoType echoType = CommentUtils.getEchoType(column.getComment());
        if (echoType != null) {
            tableColumn.setEchoStr(echoType.getEchoStr());
            tableColumn.setDictType(echoType.getDictType());
        }
        // 解析注释中的枚举类型
        EnumType enumType = CommentUtils.getEnumStr(genTable.getEntityName(), tableColumn.getJavaField(), entityConfig.getFormatEnumFileName(), tableColumn.getSwaggerComment(), column.getComment());
        if (enumType != null) {
            tableColumn.setEnumStr(enumType.getEnumStr());
            tableColumn.setJavaType(enumType.getEnumName());
            tableColumn.setEchoStr("@Echo(api = Echo.ENUM_API)");
            tableColumn.setTsType("string");

            tableColumn.setComponent(ComponentEnum.PLUS_API_RADIO_GROUP.getValue());
            tableColumn.setVxeComponent(VxeComponentEnum.$RADIO.getValue());
            tableColumn.setQueryType(SqlConditionEnum.EQUAL);
        } else {
            ITypeConvert typeConvert = TypeConverts.getTypeConvert(dbType);
            ColumnType columnType = typeConvert.processTypeConvert(entityConfig.getDateType(), column.getTypeName(), column.getSize(), column.getDigit());
            tableColumn.setJavaType(columnType.getType());
            tableColumn.setTsType(columnType.getTsType());
            if (columnType == DbColumnType.STRING) {
                tableColumn.setQueryType(SqlConditionEnum.LIKE);
            } else {
                tableColumn.setQueryType(SqlConditionEnum.EQUAL);
            }

            /*
            默认：Input
            名称含有Password： InputPassword
            名称含有remarks： InputTextArea
            字典、枚举： ApiSelect、ApiRadioGroup
            Boolean: RadioGroup
            LocalDate、LocalDateTime： DatePicker
            LocalTime： TimePicker
            */
            String component = columnType.getComponent();
            String vxeComponent = columnType.getVxeComponent();
            if (StrUtil.containsIgnoreCase(tableColumn.getJavaField(), "password")) {
                component = ComponentEnum.PLUS_INPUT_PASSWORD.getValue();
            } else if (StrUtil.containsIgnoreCase(tableColumn.getJavaField(), "remark")) {
                component = ComponentEnum.PLUS_INPUT_TEXT_AREA.getValue();
                vxeComponent = VxeComponentEnum.$TEXTAREA.getValue();
            }
            if (StrUtil.isNotEmpty(tableColumn.getEchoStr()) || StrUtil.isNotEmpty(tableColumn.getEnumStr())) {
                component = ComponentEnum.PLUS_API_RADIO_GROUP.getValue();
                vxeComponent = VxeComponentEnum.$RADIO.getValue();
            }
            tableColumn.setComponent(component);
            tableColumn.setVxeComponent(vxeComponent);
        }

        tableColumn.setTableId(genTable.getId());
        tableColumn.setName(name);
        tableColumn.setType(column.getTypeName());
        tableColumn.setSize(column.getSize());
        tableColumn.setIsPk(column.isPk());
        tableColumn.setIsRequired(!column.isNullable());
        tableColumn.setIsIncrement(column.isAutoIncrement());

        String versionPropertyName = entityConfig.getVersionPropertyName();
        String versionColumnName = entityConfig.getVersionColumnName();
        tableColumn.setIsVersionField(StringUtils.isNotBlank(versionPropertyName) && tableColumn.getJavaField().equals(versionPropertyName) || StringUtils.isNotBlank(versionColumnName) && tableColumn.getName().equalsIgnoreCase(versionColumnName));

        String logicDeleteColumnName = entityConfig.getLogicDeleteColumnName();
        String logicDeletePropertyName = entityConfig.getLogicDeletePropertyName();
        tableColumn.setIsLogicDeleteField(StringUtils.isNotBlank(logicDeletePropertyName) && tableColumn.getJavaField().equals(logicDeletePropertyName) || StringUtils.isNotBlank(logicDeleteColumnName) && tableColumn.getName().equalsIgnoreCase(logicDeleteColumnName));

        Map<String, FieldFill> fillColumnName = entityConfig.getFillColumnName();
        Map<String, FieldFill> fillPropertyName = entityConfig.getFillPropertyName();
        if (CollUtil.isNotEmpty(fillPropertyName) && fillPropertyName.containsKey(tableColumn.getJavaField())) {
            tableColumn.setFill(fillPropertyName.get(tableColumn.getJavaField()));
        }
        if (CollUtil.isNotEmpty(fillColumnName) && fillColumnName.containsKey(tableColumn.getName())) {
            tableColumn.setFill(fillColumnName.get(tableColumn.getName()));
        }

        // 编辑字段
        if (!ArrayUtil.contains(GenConstants.NOT_EDIT, name) && !column.isPk() && !tableColumn.getIsLogicDeleteField()) {
            tableColumn.setIsEdit(true);
        }
        // 列表字段
        if (!ArrayUtil.contains(GenConstants.NOT_LIST, name) && !column.isPk() && !tableColumn.getIsLogicDeleteField()) {
            tableColumn.setIsList(true);
        }
        // 查询字段
        if (!ArrayUtil.contains(GenConstants.NOT_QUERY, name) && !column.isPk() && !tableColumn.getIsLogicDeleteField()) {
            tableColumn.setIsQuery(true);
        }

        tableColumn.setEditDefValue(getDefValue(column.getColumnDef()));

        return tableColumn;
    }

    private static String getDefValue(String columnDef) {
        if (StrUtil.isEmpty(columnDef)) {
            return StrPool.EMPTY;
        }
        return switch (columnDef) {
            case "b'0'" -> "false";
            case "b'1'" -> "true";
            default -> columnDef;
        };
    }
}
