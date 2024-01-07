package top.tangyh.lamp.model.enumeration.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 企业
 * </p>
 *
 * @author zuihou
 * @date 2021-09-27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "状态-枚举")
public enum DefTenantStatusEnum implements BaseEnum {

    /**
     * NORMAL="正常"
     */
    NORMAL("05", "正常"),
    /**
     * WAIT_INIT_SCHEMA="待初始化表结构和数据"
     */
    WAIT_INIT_SCHEMA("10", "待初始化结构"),
    /**
     * WAIT_INIT_DATASOURCE="待初始化表结构和数据"
     */
    WAIT_INIT_DATASOURCE("15", "待初始化数据源"),
    /**
     * WITHDRAW="已撤回"
     */
    WITHDRAW("20", "已撤回"),
    /**
     * WAITING="待审核"
     */
    WAITING("25", "待审核"),
    /**
     * REFUSE="已拒绝"
     */
    REFUSE("30", "已拒绝"),
    /**
     * AGREED="已同意"
     */
    AGREED("35", "已同意"),
    ;

    @Schema(description = "描述")
    private String code;
    @Schema(description = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static DefTenantStatusEnum match(String val, DefTenantStatusEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static DefTenantStatusEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(DefTenantStatusEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码")
    public String getCode() {
        return this.code;
    }

}
