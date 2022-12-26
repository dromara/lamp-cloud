package top.tangyh.lamp.model.enumeration.base;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 机构类型
 * </p>
 *
 * @author zuihou
 * @date 2021-11-08
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="机构类型-枚举")
public enum OrgTypeEnum implements BaseEnum {

    /**
     * 单位
     */
    COMPANY("01", "单位"),
    /**
     * 部门
     */
    DEPT("02", "部门"),
    ;

    private String code;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static OrgTypeEnum match(String val, OrgTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static OrgTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(OrgTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description="编码", allowableValues = "10,20", example = "10")
    public String getCode() {
        return this.code;
    }


}
