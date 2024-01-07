package top.tangyh.lamp.datascope.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2019-07-21
 */
@Getter
@AllArgsConstructor
@Schema(description = "数据权限类型-枚举")
public enum DataScopeEnum implements BaseEnum {
    /**
     * 全部
     */
    ALL("01", "全部"),
    /**
     * 本单位及子级
     */
    SELF_COMPANY_CHILDREN("02", "本单位及子级"),
    /**
     * 本单位
     */
    SELF_COMPANY("03", "本单位"),
    /**
     * 本部门及子级
     */
    SELF_DEPT_CHILDREN("04", "本部门及子级"),
    SELF_DEPT("05", "本部门"),
    /**
     * SELF=个人
     */
    SELF("06", "个人"),
    CUSTOM("07", "自定义"),
    ;

    @Schema(description = "描述")
    private final String val;

    private final String desc;


    public static DataScopeEnum match(String val, DataScopeEnum def) {
        return Stream.of(values()).parallel().filter((item) -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static DataScopeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(final DataScopeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "ALL,SELF_COMPANY_CHILDREN,SELF_COMPANY,SELF_DEPT_CHILDREN,SELF_DEPT,SELF,CUSTOM", example = "ALL")
    public String getCode() {
        return this.val;
    }

}
