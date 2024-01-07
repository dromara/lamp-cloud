package top.tangyh.lamp.generator.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.tangyh.lamp.generator.enumeration.EntitySuperClassEnum;
import top.tangyh.lamp.generator.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实体配置
 *
 * @author zuihou
 * @date 2022/3/9 21:40
 */
@Data
@NoArgsConstructor
public class EntityConfig {
    /**
     * 时间类型对应策略
     */
    private DateType dateType = DateType.TIME_PACK;
    /**
     * Entity类的父类
     */
    private EntitySuperClassEnum entitySuperClass = EntitySuperClassEnum.ENTITY;

    /**
     * 指定生成的主键的ID类型 (${superClass} == NONE 时，新生成的实体才生效)
     * <p>
     * 只会影响新生成的实体类的主键类型，并不会影响已经生成好的实体类或 SuperEntity 上标记的
     */
    private IdType idType = IdType.INPUT;
    /**
     * 数据库表字段映射到实体的命名策略
     * <p>未指定按照 naming 执行</p>
     */
    private NamingStrategy columnNaming = NamingStrategy.underline_to_camel;
    /**
     * 忽略字段（字段名）
     * 配置后，导入表时，不会将配置的字段导入
     */
    private List<String> ignoreColumns = new ArrayList<>();

    /**
     * 【实体】是否生成字段常量（默认 false）<br>
     * -----------------------------------<br>
     * public static final String ID = "test_id";
     */
    private Boolean columnConstant = false;

    /**
     * 【实体、VO】是否为链式模型（默认 false）<br>
     * -----------------------------------<br>
     * <code>
     *     public User setName(String name) { this.name = name; return this; }
     * </code>
     *
     * @since 3.3.2
     */
    private Boolean chain = true;

    /**
     * 【实体、VO】是否为lombok模型（默认 false）<br>
     * <a href="https://projectlombok.org/">document</a>
     */
    private Boolean lombok = true;

    /**
     * 乐观锁字段名称(数据库字段)
     *
     * @since 3.5.0
     */
    private String versionColumnName;

    /**
     * 乐观锁属性名称(实体字段)
     *
     * @since 3.5.0
     */
    private String versionPropertyName;

    /**
     * 逻辑删除字段名称(数据库字段)
     *
     * @since 3.5.0
     */
    private String logicDeleteColumnName;

    /**
     * 逻辑删除属性名称(实体字段)
     *
     * @since 3.5.0
     */
    private String logicDeletePropertyName;
    /**
     * 除了父类实体中存在的字段外，需要填充的字段(数据库字段)
     *
     * @since 3.5.0
     */
    private Map<String, FieldFill> fillColumnName;

    /**
     * 除了父类实体中存在的字段外，需要填充的字段(实体字段)
     *
     * @since 3.5.0
     */
    private Map<String, FieldFill> fillPropertyName;


    /**
     * 格式化Enum文件名称
     * 默认： entityName + GenCodeConstant.Enum
     */
    private String formatEnumFileName;
    /**
     * 格式化SaveVO文件名称
     * 默认： entityName + GenCodeConstant.SAVE_VO
     */
    private String formatSaveVoFileName;
    /**
     * 格式化UpdateVO文件名称
     * 默认： entityName + GenCodeConstant.UPDATE_VO
     */
    private String formatUpdateVoFileName;
    /**
     * 格式化ResultVO文件名称
     * 默认： entityName + GenCodeConstant.RESULT_VO
     */
    private String formatResultVoFileName;
    /**
     * 格式化 PageQuery 文件名称
     * 默认： entityName + GenCodeConstant.PAGE_QUERY
     */
    private String formatPageQueryFileName;

}
