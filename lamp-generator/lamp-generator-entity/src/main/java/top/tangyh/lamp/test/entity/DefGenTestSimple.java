package top.tangyh.lamp.test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.lamp.test.enumeration.DefGenTestSimpleType2Enum;
import top.tangyh.lamp.test.enumeration.ProductType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;
import static top.tangyh.lamp.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 测试单表
 * </p>
 *
 * @author zuihou
 * @date 2022-04-15 15:36:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("def_gen_test_simple")
public class DefGenTestSimple extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 库存
     */
    @TableField(value = "stock", condition = EQUAL)
    private Integer stock;
    /**
     * 商品类型;
     * #ProductType{ordinary:普通;gift:赠品}
     */
    @TableField(value = "type_", condition = EQUAL)
    private ProductType type;
    /**
     * 商品类型2 ;
     * <p>
     * #{ordinary:01,普通;gift:02,赠品;}
     */
    @TableField(value = "type2", condition = EQUAL)
    private DefGenTestSimpleType2Enum type2;
    /**
     * 学历;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS,  dictType = DictionaryType.Global.EDUCATION)
     */
    @TableField(value = "type3", condition = LIKE)
    private String type3;
    /**
     * 状态
     */
    @TableField(value = "state", condition = EQUAL)
    private Boolean state;
    /**
     * 测试
     */
    @TableField(value = "test4", condition = EQUAL)
    private Integer test4;
    /**
     * 时间
     */
    @TableField(value = "test5", condition = EQUAL)
    private LocalDate test5;
    /**
     * 日期
     */
    @TableField(value = "test6", condition = EQUAL)
    private LocalDateTime test6;
    /**
     * 父id
     */
    @TableField(value = "parent_id", condition = EQUAL)
    private Long parentId;
    /**
     * 名称
     */
    @TableField(value = "label", condition = LIKE)
    private String label;
    /**
     * 排序
     */
    @TableField(value = "sort_value", condition = EQUAL)
    private Integer sortValue;
    /**
     * 字符字典;
     *
     * @Echo(api = "top.tangyh.lamp.oauth.api.DictionaryApi", dictType="GLOBAL_SEX")
     */
    @TableField(value = "test7", condition = LIKE)
    private String test7;
    /**
     * 整形字典;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = DictionaryType.Global.DATA_TYPE)
     */
    @TableField(value = "test12", condition = EQUAL)
    private Integer test12;
    /**
     * 用户;
     *
     * @Echo(api = EchoApi.POSITION_ID_CLASS)
     */
    @TableField(value = "user_id", condition = EQUAL)
    private Long userId;
    /**
     * 组织;
     *
     * @Echo(api = EchoApi.ORG_ID_CLASS)
     */
    @TableField(value = "org_id", condition = EQUAL)
    private Long orgId;
    /**
     * 小数
     */
    @TableField(value = "test8", condition = EQUAL)
    private BigDecimal test8;
    /**
     * 浮点2
     */
    @TableField(value = "test9", condition = EQUAL)
    private Float test9;
    /**
     * 浮点
     */
    @TableField(value = "test10", condition = EQUAL)
    private Double test10;
    /**
     * xiao树
     */
    @TableField(value = "test11", condition = EQUAL)
    private BigDecimal test11;


}
