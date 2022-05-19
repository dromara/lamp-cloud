package top.tangyh.lamp.authority.enumeration.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

/**
 * 资源类型
 *
 * @author zuihou
 * @since 2021/3/12 21:20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ResourceTypeEnum", description = "资源类型-枚举")
public enum ResourceTypeEnum implements BaseEnum {
    /**
     * 菜单
     */
    MENU("20", "菜单"),
    /**
     * 视图
     */
    VIEW("30", "视图"),
    /**
     * 功能
     */
    FUNCTION("40", "功能"),
    /**
     * 字段
     */
    FIELD("50", "字段"),

    /**
     * 数据权限
     */
    DATA("60", "数据");

    /**
     * 资源类型
     */
    private String code;

    /**
     * 资源描述
     */
    private String desc;

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "20,30,40,50,60", example = "20")
    public String getCode() {
        return this.code;
    }
}
