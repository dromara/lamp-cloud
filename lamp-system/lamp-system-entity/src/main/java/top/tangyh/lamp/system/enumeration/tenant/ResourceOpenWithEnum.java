package top.tangyh.lamp.system.enumeration.tenant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

/**
 * 资源 打开方式
 * [01-组件 02-内链 03-外链]
 *
 * @author zuihou
 * @since 2021/3/12 21:20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "资源类型-打开方式")
public enum ResourceOpenWithEnum implements BaseEnum {
    /**
     * 组件
     */
    INNER_COMPONENT("01", "菜单"),
    /**
     * 内链
     */
    INNER_CHAIN("02", "内链"),
    /**
     * 外链
     */
    OUTER_CHAIN("03", "外链"),

    ;


    /**
     * 打开方式
     */
    private String type;

    /**
     * 描述
     */
    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    @Schema(description = "编码", allowableValues = "01,02,03", example = "01")
    public String getCode() {
        return this.type;
    }
}
