package top.tangyh.lamp.generator.utils.inner;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperReadController;
import top.tangyh.basic.base.controller.SuperSimpleController;
import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.basic.base.manager.impl.SuperCacheManagerImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.interfaces.BaseEnum;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.basic.interfaces.echo.EchoVO;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.basic.utils.TreeUtil;

import top.tangyh.lamp.generator.config.ControllerConfig;
import top.tangyh.lamp.generator.config.EntityConfig;
import top.tangyh.lamp.generator.config.GeneratorConfig;
import top.tangyh.lamp.generator.config.ManagerConfig;
import top.tangyh.lamp.generator.config.MapperConfig;
import top.tangyh.lamp.generator.config.PackageInfoConfig;
import top.tangyh.lamp.generator.config.ServiceConfig;
import top.tangyh.lamp.generator.converts.ITypeConvert;
import top.tangyh.lamp.generator.converts.TypeConverts;
import top.tangyh.lamp.generator.entity.DefGenTable;
import top.tangyh.lamp.generator.entity.DefGenTableColumn;
import top.tangyh.lamp.generator.enumeration.EntitySuperClassEnum;
import top.tangyh.lamp.generator.enumeration.SuperClassEnum;
import top.tangyh.lamp.generator.enumeration.TplEnum;
import top.tangyh.lamp.generator.rules.ColumnType;
import top.tangyh.lamp.generator.rules.echo.EchoType;
import top.tangyh.lamp.generator.rules.enumeration.EnumType;
import top.tangyh.lamp.generator.utils.GenCodeConstant;
import top.tangyh.lamp.model.constant.EchoApi;
import top.tangyh.lamp.model.constant.EchoDictType;
import top.tangyh.lamp.model.constant.EchoRef;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * 包 工具类
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/4/2 7:49 PM
 * @create [2022/4/2 7:49 PM ] [tangyh] [初始创建]
 */
@Slf4j
public class PackageUtils {
    static final Set<String> ECHO_API_LIST = new HashSet<>();
    static final Set<String> ECHO_REF_LIST = new HashSet<>();
    static final Map<String, Set<String>> ECHO_DICT_TYPE_MAP = new HashMap<>();

    static {
        Field[] apiFields = ReflectUtil.getFields(EchoApi.class);
        for (Field field : apiFields) {
            ECHO_API_LIST.add(field.getName());
        }
        Field[] refFields = ReflectUtil.getFields(EchoRef.class);
        for (Field field : refFields) {
            ECHO_REF_LIST.add(field.getName());
        }

        putDictType(EchoDictType.class);
        putDictType(EchoDictType.Global.class);
        putDictType(EchoDictType.Base.class);
        putDictType(EchoDictType.System.class);
        putDictType(EchoDictType.Oauth.class);
        putDictType(EchoDictType.File.class);
        putDictType(EchoDictType.Msg.class);
        putDictType(EchoDictType.Gateway.class);
    }

    private static void putDictType(Class<?> clazz) {
        Field[] dictGlobalFields = ReflectUtil.getFields(clazz);
        Set<String> list = new HashSet<>();
        for (Field field : dictGlobalFields) {
            list.add(field.getName());
        }
        ECHO_DICT_TYPE_MAP.put(clazz.getSimpleName(), list);
    }

    /**
     * 获取类名
     *
     * @param className className 全类名
     * @return ignore
     */
    private static String getSimpleName(String className) {
        return StringUtils.isBlank(className) ? null : className.substring(className.lastIndexOf(StringPool.DOT) + 1);
    }

    private static String joinPackage(DefGenTable genTable, GeneratorConfig generatorConfig, String functionPackage) {
        ArgumentAssert.notNull(functionPackage, "功能包名不能为空");
        PackageInfoConfig packageInfoConfig = generatorConfig.getPackageInfoConfig();
        String parent = StrUtil.isEmpty(genTable.getParent()) ? packageInfoConfig.getParent() : genTable.getParent();
        String moduleName = genTable.getModuleName();
        String childPackageName = genTable.getChildPackageName();
        StringBuilder join = new StringBuilder();
        if (StrUtil.isNotEmpty(parent)) {
            join.append(parent);
        }
        boolean end = StrUtil.endWith(join, StrPool.DOT);
        if (StrUtil.isNotEmpty(moduleName)) {
            if (end) {
                join.append(moduleName);
            } else {
                join.append(StrPool.DOT).append(moduleName);
            }
        }

        end = StrUtil.endWith(join, StrPool.DOT);
        if (StrUtil.isNotEmpty(functionPackage)) {
            if (end) {
                join.append(functionPackage);
            } else {
                join.append(StrPool.DOT).append(functionPackage);
            }
        }


        if (StrUtil.isNotEmpty(childPackageName)) {
            if (StrUtil.contains(join.toString(), StrPool.BRACE)) {
                join = new StringBuilder(StrUtil.format(join.toString(), childPackageName + StrPool.DOT));
            } else {
                end = StrUtil.endWith(join, StrPool.DOT);
                if (end) {
                    join.append(childPackageName);
                } else {
                    join.append(StrPool.DOT).append(childPackageName);
                }
            }
        } else {
            if (StrUtil.contains(join.toString(), StrPool.BRACE)) {
                join = new StringBuilder(StrUtil.format(join.toString(), StrPool.EMPTY));
            }
        }

        return join.toString();
    }


    public static Map<String, Object> getPackage(DefGenTable genTable, GeneratorConfig generatorConfig) {
        Map<String, Object> map = new HashMap<>();
        PackageInfoConfig packageInfoConfig = generatorConfig.getPackageInfoConfig();
        map.put(GenCodeConstant.ENUM, joinPackage(genTable, generatorConfig, packageInfoConfig.getEnumeration()));
        map.put(GenCodeConstant.SAVE_VO, joinPackage(genTable, generatorConfig, packageInfoConfig.getSaveVo()));
        map.put(GenCodeConstant.UPDATE_VO, joinPackage(genTable, generatorConfig, packageInfoConfig.getUpdateVo()));
        map.put(GenCodeConstant.RESULT_VO, joinPackage(genTable, generatorConfig, packageInfoConfig.getResultVo()));
        map.put(GenCodeConstant.PAGE_QUERY, joinPackage(genTable, generatorConfig, packageInfoConfig.getPageQuery()));
        map.put(GenCodeConstant.ENTITY, joinPackage(genTable, generatorConfig, packageInfoConfig.getEntity()));
        map.put(GenCodeConstant.MAPPER, joinPackage(genTable, generatorConfig, packageInfoConfig.getMapper()));
        map.put(GenCodeConstant.MANAGER, joinPackage(genTable, generatorConfig, packageInfoConfig.getManager()));
        map.put(GenCodeConstant.MANAGER_IMPL, joinPackage(genTable, generatorConfig, packageInfoConfig.getManagerImpl()));
        map.put(GenCodeConstant.SERVICE, joinPackage(genTable, generatorConfig, packageInfoConfig.getService()));
        map.put(GenCodeConstant.SERVICE_IMPL, joinPackage(genTable, generatorConfig, packageInfoConfig.getServiceImpl()));
        map.put(GenCodeConstant.CONTROLLER, joinPackage(genTable, generatorConfig, packageInfoConfig.getController()));
        return map;
    }


    public static Map<String, Object> getSuperClassPackage(DefGenTable genTable) {
        Map<String, Object> packageMap = new HashMap<>();
        SuperClassEnum superClass = genTable.getSuperClass();
        EntitySuperClassEnum entitySuperClass = genTable.getEntitySuperClass();

        packageMap.put("superControllerClassPackage", superClass.getController());
        packageMap.put("superControllerClass", getSimpleName(superClass.getController()));

        packageMap.put("superServiceClassPackage", superClass.getService());
        packageMap.put("superServiceClass", getSimpleName(superClass.getService()));
        packageMap.put("superServiceImplClassPackage", superClass.getServiceImpl());
        packageMap.put("superServiceImplClass", getSimpleName(superClass.getServiceImpl()));

        packageMap.put("superManagerClassPackage", superClass.getManager());
        packageMap.put("superManagerImplClassPackage", superClass.getManagerImpl());
        packageMap.put("superManagerClass", getSimpleName(superClass.getManager()));
        packageMap.put("superManagerImplClass", getSimpleName(superClass.getManagerImpl()));

        packageMap.put("superMapperClassPackage", superClass.getMapper());
        packageMap.put("superMapperClass", getSimpleName(superClass.getMapper()));

        packageMap.put("superEntityClassPackage", entitySuperClass.getClazzName());
        packageMap.put("superEntityClass", getSimpleName(entitySuperClass.getClazzName()));

        packageMap.put("superReadControllerSimpleName", SuperReadController.class.getSimpleName());
        packageMap.put("superSimpleControllerSimpleName", SuperSimpleController.class.getSimpleName());
        packageMap.put("superCacheManagerImplSimpleName", SuperCacheManagerImpl.class.getSimpleName());
        return packageMap;
    }

    public static Map<String, Object> getImportPackages(DbType dbType, DefGenTable genTable, GeneratorConfig generatorConfig, List<DefGenTableColumn> fieldList, Map<String, Object> objectMap) {
        Map<String, Object> map = new HashMap<>();
        Set<String> saveVoImportPackages = new TreeSet<>();
        Set<String> updateVoImportPackages = new TreeSet<>();
        Set<String> resultVoImportPackages = new TreeSet<>();
        Set<String> pageQueryImportPackages = new TreeSet<>();
        Set<String> entityImportPackages = new TreeSet<>();
        Set<String> mapperImportPackages = new TreeSet<>();
        Set<String> managerImportPackages = new TreeSet<>();
        Set<String> managerImplImportPackages = new TreeSet<>();
        Set<String> serviceImportPackages = new TreeSet<>();
        Set<String> serviceImplImportPackages = new TreeSet<>();
        Set<String> controllerImportPackages = new TreeSet<>();
        Set<String> enumImportPackages = new TreeSet<>();
        Set<String> dataTsImport = new TreeSet<>();
        EntityConfig entityConfig = generatorConfig.getEntityConfig();

        importVO(genTable, saveVoImportPackages, updateVoImportPackages, resultVoImportPackages, pageQueryImportPackages, entityImportPackages);

        Set<EnumType> etList = new HashSet<>();
        Set<EchoType> echoList = new HashSet<>();
        fieldList.forEach(field -> {
            // 导入
            importType(dbType, saveVoImportPackages, updateVoImportPackages, resultVoImportPackages, pageQueryImportPackages, entityImportPackages, entityConfig, field);

            // 表单验证
            importValid(saveVoImportPackages, updateVoImportPackages, field);

            importEntity(entityImportPackages, entityConfig, field);

            // 枚举字段
            importEnum(genTable, generatorConfig, objectMap, saveVoImportPackages, updateVoImportPackages, resultVoImportPackages, pageQueryImportPackages, entityImportPackages, enumImportPackages, entityConfig, etList, field);

            // Echo 注解
            importEcho(generatorConfig, resultVoImportPackages, echoList, field);

            // 前端类型导入
            importTs(generatorConfig, dataTsImport, field);

        });

        // service Impl 导入包开始
        importServiceImpl(genTable, serviceImplImportPackages);

        // manager Impl 导入包开始
        importManagerImpl(genTable, managerImplImportPackages);

        // controller 导入包开始
        importController(genTable, controllerImportPackages);

        // 树结构特殊导入
        importTreeEntity(genTable, serviceImportPackages, serviceImplImportPackages, controllerImportPackages);

        // 主从相关
        importMainSub(genTable, objectMap, saveVoImportPackages, updateVoImportPackages, serviceImplImportPackages);

        if (CollUtil.isNotEmpty(echoList)) {
            Set<String> refList = new HashSet<>();
            Set<String> apiList = new HashSet<>();
            Map<String, Set<String>> dictTypeMap = new HashMap<>();

            echoList.forEach(et -> {
                Set<String> dictList = ECHO_DICT_TYPE_MAP.getOrDefault(et.getDictTypePosition(), Collections.emptySet());
                if (StrUtil.isNotEmpty(et.getDictTypeConstants()) && !dictList.contains(et.getDictTypeField())) {
                    if (dictTypeMap.containsKey(et.getDictTypePosition())) {
                        Set<String> sets = dictTypeMap.get(et.getDictTypePosition());
                        sets.add(et.getDictTypeConstants());
                        dictTypeMap.put(et.getDictTypePosition(), sets);
                    } else {
                        Set<String> sets = new HashSet<>();
                        sets.add(et.getDictTypeConstants());
                        dictTypeMap.put(et.getDictTypePosition(), sets);
                    }
                } else {
                    log.info("忽略生成 Echo#dictType : {}", et.getDictTypeConstants());
                }
                if (StrUtil.isNotEmpty(et.getRefConstants()) && !ECHO_REF_LIST.contains(et.getRefField())) {
                    refList.add(et.getRefConstants());
                } else {
                    log.info("忽略生成 Echo#ref : {}", et.getRefConstants());
                }
                boolean flag = !ECHO_API_LIST.contains(et.getApiField()) && !GenCodeConstant.ECHO_ENUM_API.equals(et.getApi());
                if (StrUtil.isNotEmpty(et.getApiConstants()) && flag) {
                    apiList.add(et.getApiConstants());
                } else {
                    log.info("忽略生成 Echo#api : {}", et.getApiField());
                }
            });

            map.put(GenCodeConstant.ECHO_API_LIST, apiList);
            map.put(GenCodeConstant.ECHO_REF_LIST, refList);
            map.put(GenCodeConstant.ECHO_DICT_TYPE_MAP, dictTypeMap);
        }

        map.put(GenCodeConstant.ET_LIST, etList);
        map.put(GenCodeConstant.ECHO_LIST, echoList);
        map.put("dataTsImport", dataTsImport);
        map.put("enumImport", enumImportPackages);
        map.put("saveVoImport", saveVoImportPackages);
        map.put("updateVoImport", updateVoImportPackages);
        map.put("resultVoImport", resultVoImportPackages);
        map.put("pageQueryImport", pageQueryImportPackages);
        map.put("entityImport", entityImportPackages);
        map.put("mapperImport", mapperImportPackages);
        map.put("managerImport", managerImportPackages);
        map.put("managerImplImport", managerImplImportPackages);
        map.put("serviceImport", serviceImportPackages);
        map.put("serviceImplImport", serviceImplImportPackages);
        map.put("controllerImport", controllerImportPackages);
        return map;
    }


    private static void importType(DbType dbType, Set<String> saveVoImportPackages, Set<String> updateVoImportPackages, Set<String> resultVoImportPackages,
                                   Set<String> pageQueryImportPackages, Set<String> entityImportPackages, EntityConfig entityConfig, DefGenTableColumn field) {
        ITypeConvert typeConvert = TypeConverts.getTypeConvert(dbType);
        ColumnType columnType = typeConvert.processTypeConvert(entityConfig.getDateType(), field.getType(), field.getSize(), field.getDigit());
        if (columnType != null && columnType.getPkg() != null) {
            entityImportPackages.add(columnType.getPkg());
            saveVoImportPackages.add(columnType.getPkg());
            updateVoImportPackages.add(columnType.getPkg());
            resultVoImportPackages.add(columnType.getPkg());
            pageQueryImportPackages.add(columnType.getPkg());
        }
    }

    private static void importValid(Set<String> saveVoImportPackages, Set<String> updateVoImportPackages, DefGenTableColumn field) {
        if (field.getIsPk() || field.getIsRequired()) {
            if ("String".equals(field.getJavaType())) {
                saveVoImportPackages.add(NotEmpty.class.getCanonicalName());
                updateVoImportPackages.add(NotEmpty.class.getCanonicalName());
            } else {
                saveVoImportPackages.add(NotNull.class.getCanonicalName());
                updateVoImportPackages.add(NotNull.class.getCanonicalName());
            }
        }
        if ("String".equals(field.getJavaType()) || "SHORT".equalsIgnoreCase(field.getType()) || "BYTE".equalsIgnoreCase(field.getType())) {
            saveVoImportPackages.add(Size.class.getCanonicalName());
            updateVoImportPackages.add(Size.class.getCanonicalName());
        }
    }

    private static void importEntity(Set<String> entityImportPackages, EntityConfig entityConfig, DefGenTableColumn field) {
        if (field.getIsPk()) {
            entityImportPackages.add(TableId.class.getCanonicalName());
            if (field.getIsIncrement() || entityConfig.getIdType() != null) {
                entityImportPackages.add(IdType.class.getCanonicalName());
            }
        } else {
            entityImportPackages.add(TableField.class.getCanonicalName());

            if (field.getFill() != null) {
                entityImportPackages.add(FieldFill.class.getCanonicalName());
            }
        }
        if (field.getIsVersionField()) {
            entityImportPackages.add(Version.class.getCanonicalName());
        }
        if (field.getIsLogicDeleteField()) {
            entityImportPackages.add(TableLogic.class.getCanonicalName());
        }
        if (field.getQueryType() != null) {
            entityImportPackages.add(field.getQueryType().getDesc());
        }
        entityImportPackages.add(Schema.class.getCanonicalName());
    }

    private static void importEnum(DefGenTable genTable, GeneratorConfig generatorConfig, Map<String, Object> objectMap, Set<String> saveVoImportPackages, Set<String> updateVoImportPackages, Set<String> resultVoImportPackages, Set<String> pageQueryImportPackages, Set<String> entityImportPackages, Set<String> enumImportPackages, EntityConfig entityConfig, Set<EnumType> etList, DefGenTableColumn field) {
        if (StrUtil.isNotEmpty(field.getEnumStr())) {
            EnumType enumType = CommentUtils.getEnumStr(genTable.getEntityName(), field.getJavaField(), entityConfig.getFormatEnumFileName(), field.getSwaggerComment(), field.getEnumStr());
            if (enumType != null) {
                String enumName = enumType.getEnumName();
                Map<String, Object> packageMap = (Map<String, Object>) objectMap.get("package");
                String enumPackage = packageMap.get(GenCodeConstant.ENUM) + StrPool.DOT + enumName;
                enumType.setEnumPackage(enumPackage);

                Map<String, Class<?>> constantsPackage = generatorConfig.getConstantsPackage();
                Class<?> enumClazz = constantsPackage.get(enumType.getEnumName());
                if (enumClazz != null) {
                    entityImportPackages.add(enumClazz.getCanonicalName());
                    saveVoImportPackages.add(enumClazz.getCanonicalName());
                    updateVoImportPackages.add(enumClazz.getCanonicalName());
                    pageQueryImportPackages.add(enumClazz.getCanonicalName());
                    resultVoImportPackages.add(enumClazz.getCanonicalName());
                } else {
                    entityImportPackages.add(enumPackage);
                    saveVoImportPackages.add(enumPackage);
                    updateVoImportPackages.add(enumPackage);
                    pageQueryImportPackages.add(enumPackage);
                    resultVoImportPackages.add(enumPackage);

                    etList.add(enumType);
                }
                enumImportPackages.add(BaseEnum.class.getCanonicalName());
                enumImportPackages.add(Schema.class.getCanonicalName());
                enumImportPackages.add(AllArgsConstructor.class.getCanonicalName());
                enumImportPackages.add(NoArgsConstructor.class.getCanonicalName());
                enumImportPackages.add(Getter.class.getCanonicalName());
                enumImportPackages.add(Stream.class.getCanonicalName());
            }
        }
    }


    private static void importEcho(GeneratorConfig generatorConfig, Set<String> resultVoImportPackages, Set<EchoType> echoList, DefGenTableColumn field) {
        if (StrUtil.isNotEmpty(field.getEchoStr())) {
            EchoType echoType = CommentUtils.getEchoType(field.getEchoStr());
            if (echoType != null) {
                echoList.add(echoType);
                resultVoImportPackages.add(Echo.class.getCanonicalName());
                resultVoImportPackages.add(EchoVO.class.getCanonicalName());
                Map<String, Class<?>> constantsPackage = generatorConfig.getConstantsPackage();
                constantsPackage.forEach((type, clazz) -> {
                    if (StrUtil.contains(echoType.getApi(), type)) {
                        resultVoImportPackages.add(clazz.getCanonicalName());
                    }
                    if (StrUtil.contains(echoType.getDictType(), type)) {
                        resultVoImportPackages.add(clazz.getCanonicalName());
                    }
                    if (StrUtil.contains(echoType.getBeanClass(), type)) {
                        resultVoImportPackages.add(clazz.getCanonicalName());
                    }
                });
            }
        }
    }

    private static void importTs(GeneratorConfig generatorConfig, Set<String> dataTsImport, DefGenTableColumn field) {
        if (LocalDateTime.class.getSimpleName().equals(field.getJavaType()) || LocalDate.class.getSimpleName().equals(field.getJavaType()) || LocalTime.class.getSimpleName().equals(field.getJavaType())) {
            dataTsImport.add("{ dateUtil } from '/@/utils/dateUtil'");
        }
        if (Boolean.class.getSimpleName().equals(field.getJavaType())) {
            dataTsImport.add("{ yesNoComponentProps } from '/@/utils/" + generatorConfig.getProjectPrefix() + "/common'");
        }

        if (StrUtil.isNotEmpty(field.getEnumStr())) {
            dataTsImport.add("{ enumComponentProps } from '/@/utils/" + generatorConfig.getProjectPrefix() + "/common'");
            dataTsImport.add("{ EnumEnum } from '/@/enums/commonEnum'");
        }
        if (StrUtil.isNotEmpty(field.getDictType()) && !StrUtil.contains(field.getDictType(), "\"")) {
            dataTsImport.add("{ DictEnum } from '/@/enums/commonEnum'");
            dataTsImport.add("{ dictComponentProps } from '/@/utils/" + generatorConfig.getProjectPrefix() + "/common'");
        }
    }

    private static void importVO(DefGenTable genTable, Set<String> saveVoImportPackages, Set<String> updateVoImportPackages, Set<String> resultVoImportPackages, Set<String> pageQueryImportPackages, Set<String> entityImportPackages) {
        // VO-实体 公共部分导包
        if (genTable.getIsLombok()) {
            entityImportPackages.add(Data.class.getCanonicalName());
            entityImportPackages.add(NoArgsConstructor.class.getCanonicalName());
            entityImportPackages.add(AllArgsConstructor.class.getCanonicalName());
            entityImportPackages.add(ToString.class.getCanonicalName());
            entityImportPackages.add(EqualsAndHashCode.class.getCanonicalName());
            entityImportPackages.add(Builder.class.getCanonicalName());
            if (genTable.getIsChain()) {
                entityImportPackages.add(Accessors.class.getCanonicalName());
            }
        }

        // VO-实体 公共部分
        saveVoImportPackages.addAll(entityImportPackages);

        // VO 公共包
        saveVoImportPackages.add(Schema.class.getCanonicalName());
        updateVoImportPackages.addAll(saveVoImportPackages);
        resultVoImportPackages.addAll(saveVoImportPackages);
        pageQueryImportPackages.addAll(saveVoImportPackages);

        updateVoImportPackages.add(SuperEntity.class.getCanonicalName());

        resultVoImportPackages.add(EchoVO.class.getCanonicalName());
        resultVoImportPackages.add(MapUtil.class.getCanonicalName());
        resultVoImportPackages.add(Map.class.getCanonicalName());

        // 实体 VO
        entityImportPackages.add(TableName.class.getCanonicalName());
        EntitySuperClassEnum entitySuperClass = genTable.getEntitySuperClass();
        if (entitySuperClass != null && StrUtil.isNotEmpty(entitySuperClass.getClazzName())) {
            entityImportPackages.add(entitySuperClass.getClazzName());
            resultVoImportPackages.add(entitySuperClass.getClazzName());
        }
    }

    private static void importMainSub(DefGenTable genTable, Map<String, Object> objectMap, Set<String> saveVoImportPackages, Set<String> updateVoImportPackages, Set<String> serviceImplImportPackages) {
        if (TplEnum.MAIN_SUB.eq(genTable.getTplType())) {
            Map<String, Object> subMap = (Map<String, Object>) objectMap.get("sub");

            saveVoImportPackages.add(List.class.getCanonicalName());
            saveVoImportPackages.add((String) subMap.get(GenCodeConstant.KEY_SAVE_VO_PACKAGE));

            updateVoImportPackages.add(List.class.getCanonicalName());
            updateVoImportPackages.add((String) subMap.get(GenCodeConstant.KEY_SAVE_VO_PACKAGE));
            updateVoImportPackages.add((String) subMap.get(GenCodeConstant.KEY_UPDATE_VO_PACKAGE));

            serviceImplImportPackages.add((String) subMap.get(GenCodeConstant.KEY_SAVE_VO_PACKAGE));
            serviceImplImportPackages.add((String) subMap.get(GenCodeConstant.KEY_UPDATE_VO_PACKAGE));
            serviceImplImportPackages.add((String) subMap.get(GenCodeConstant.KEY_MANAGER_PACKAGE));
            serviceImplImportPackages.add((String) subMap.get(GenCodeConstant.KEY_ENTITY_PACKAGE));
            serviceImplImportPackages.add(ArrayList.class.getCanonicalName());
            serviceImplImportPackages.add(Collection.class.getCanonicalName());
            serviceImplImportPackages.add(List.class.getCanonicalName());
            serviceImplImportPackages.add(CollUtil.class.getCanonicalName());
            serviceImplImportPackages.add(BeanPlusUtil.class.getCanonicalName());
            serviceImplImportPackages.add(ArgumentAssert.class.getCanonicalName());
            serviceImplImportPackages.add(Wraps.class.getCanonicalName());
            serviceImplImportPackages.add(LbQueryWrap.class.getCanonicalName());
        }
    }

    private static void importTreeEntity(DefGenTable genTable, Set<String> serviceImportPackages, Set<String> serviceImplImportPackages, Set<String> controllerImportPackages) {
        if (EntitySuperClassEnum.TREE_ENTITY.eq(genTable.getEntitySuperClass())) {
            serviceImplImportPackages.add(TreeUtil.class.getCanonicalName());
            serviceImplImportPackages.add(List.class.getCanonicalName());
            serviceImplImportPackages.add(Wraps.class.getCanonicalName());

            serviceImportPackages.add(List.class.getCanonicalName());

            controllerImportPackages.add(R.class.getCanonicalName());
            controllerImportPackages.add(List.class.getCanonicalName());
            controllerImportPackages.add(RequestBody.class.getCanonicalName());
            controllerImportPackages.add(Operation.class.getCanonicalName());
            controllerImportPackages.add(PostMapping.class.getCanonicalName());
            controllerImportPackages.add(WebLog.class.getCanonicalName());
        }
    }

    private static void importServiceImpl(DefGenTable genTable, Set<String> serviceImplImportPackages) {
        if (genTable.getIsLombok()) {
            serviceImplImportPackages.add(Slf4j.class.getCanonicalName());
            serviceImplImportPackages.add(RequiredArgsConstructor.class.getCanonicalName());
        }
        serviceImplImportPackages.add(Service.class.getCanonicalName());
        serviceImplImportPackages.add(Transactional.class.getCanonicalName());
    }

    private static void importManagerImpl(DefGenTable genTable, Set<String> managerImplImportPackages) {
        if (genTable.getIsLombok()) {
            managerImplImportPackages.add(Slf4j.class.getCanonicalName());
            managerImplImportPackages.add(RequiredArgsConstructor.class.getCanonicalName());
        }
        managerImplImportPackages.add(Service.class.getCanonicalName());
        if (genTable.getSuperClass() != null && SuperClassEnum.SUPER_CACHE_CLASS.eq(genTable.getSuperClass().getCode())) {
            managerImplImportPackages.add(CacheKeyBuilder.class.getCanonicalName());
        }
    }

    private static void importController(DefGenTable genTable, Set<String> controllerImportPackages) {
        if (genTable.getIsLombok()) {
            controllerImportPackages.add(Slf4j.class.getCanonicalName());
            controllerImportPackages.add(RequiredArgsConstructor.class.getCanonicalName());
        } else {
            controllerImportPackages.add(Autowired.class.getCanonicalName());
        }
        controllerImportPackages.add(RequestMapping.class.getCanonicalName());
        controllerImportPackages.add(Tag.class.getCanonicalName());
        controllerImportPackages.add(Validated.class.getCanonicalName());
        controllerImportPackages.add(EchoService.class.getCanonicalName());
    }

    public static Map<String, Object> getMvcPackage(DefGenTable genTable, GeneratorConfig generatorConfig, Map<String, Object> packageMap) {
        MapperConfig mapperConfig = generatorConfig.getMapperConfig();
        ManagerConfig managerConfig = generatorConfig.getManagerConfig();
        ServiceConfig serviceConfig = generatorConfig.getServiceConfig();
        ControllerConfig controllerConfig = generatorConfig.getControllerConfig();
        EntityConfig entityConfig = generatorConfig.getEntityConfig();

        Map<String, Object> map = new HashMap<>();
        String mapperName = getName(genTable.getEntityName(), mapperConfig.getFormatMapperFileName(), GenCodeConstant.MAPPER);
        map.put("mapperName", mapperName);
        map.put("mapperPackage", packageMap.get(GenCodeConstant.MAPPER) + StrPool.DOT + mapperName);
        String managerName = getName(genTable.getEntityName(), managerConfig.getFormatManagerFileName(), GenCodeConstant.MANAGER);
        map.put("managerName", managerName);
        map.put(GenCodeConstant.KEY_MANAGER_PACKAGE, packageMap.get(GenCodeConstant.MANAGER) + StrPool.DOT + managerName);
        String managerImplName = getName(genTable.getEntityName(), managerConfig.getFormatManagerImplFileName(), GenCodeConstant.MANAGER_IMPL);
        map.put("managerImplName", managerImplName);
        map.put("managerImplPackage", packageMap.get(GenCodeConstant.MANAGER_IMPL) + StrPool.DOT + managerImplName);
        String serviceName = getName(genTable.getEntityName(), serviceConfig.getFormatServiceFileName(), GenCodeConstant.SERVICE);
        map.put("serviceName", serviceName);
        map.put("servicePackage", packageMap.get(GenCodeConstant.SERVICE) + StrPool.DOT + serviceName);
        String serviceImplName = getName(genTable.getEntityName(), serviceConfig.getFormatServiceImplFileName(), GenCodeConstant.SERVICE_IMPL);
        map.put("serviceImplName", serviceImplName);
        map.put("serviceImplPackage", packageMap.get(GenCodeConstant.SERVICE_IMPL) + StrPool.DOT + serviceImplName);
        String controllerName = getName(genTable.getEntityName(), controllerConfig.getFormatFileName(), GenCodeConstant.CONTROLLER);
        map.put("controllerName", controllerName);
        map.put("controllerPackage", packageMap.get(GenCodeConstant.CONTROLLER) + StrPool.DOT + controllerName);

        map.put(GenCodeConstant.KEY_ENTITY_PACKAGE, packageMap.get(GenCodeConstant.ENTITY) + StrPool.DOT + genTable.getEntityName());

        String saveVoName = getName(genTable.getEntityName(), entityConfig.getFormatSaveVoFileName(), GenCodeConstant.SAVE_VO);
        map.put("saveVoName", saveVoName);
        map.put(GenCodeConstant.KEY_SAVE_VO_PACKAGE, packageMap.get(GenCodeConstant.SAVE_VO) + StrPool.DOT + saveVoName);

        String updateVoName = getName(genTable.getEntityName(), entityConfig.getFormatUpdateVoFileName(), GenCodeConstant.UPDATE_VO);
        map.put("updateVoName", updateVoName);
        map.put(GenCodeConstant.KEY_UPDATE_VO_PACKAGE, packageMap.get(GenCodeConstant.UPDATE_VO) + StrPool.DOT + updateVoName);

        String resultVoName = getName(genTable.getEntityName(), entityConfig.getFormatResultVoFileName(), GenCodeConstant.RESULT_VO);
        map.put("resultVoName", resultVoName);
        map.put("resultVoPackage", packageMap.get(GenCodeConstant.RESULT_VO) + StrPool.DOT + resultVoName);

        String pageQueryName = getName(genTable.getEntityName(), entityConfig.getFormatPageQueryFileName(), GenCodeConstant.PAGE_QUERY);
        map.put("pageQueryName", pageQueryName);
        map.put("pageQueryPackage", packageMap.get(GenCodeConstant.PAGE_QUERY) + StrPool.DOT + pageQueryName);

        return map;
    }

    public static String getName(String entityName, String format, String defSuffix) {
        if (StrUtil.isNotEmpty(format)) {
            return StrUtil.format(format, entityName);
        } else {
            return entityName + defSuffix;
        }
    }
}
