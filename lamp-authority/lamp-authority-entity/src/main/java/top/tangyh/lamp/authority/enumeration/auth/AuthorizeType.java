package top.tangyh.lamp.authority.enumeration.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2020-11-20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "权限类型-枚举")
public enum AuthorizeType implements BaseEnum {

    /**
     * MENU="菜单"
     */
    MENU("菜单"),
    DATA("数据"),
    /**
     * RESOURCE="资源"
     */
    RESOURCE("资源"),
    ;

    @Schema(description = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static AuthorizeType match(String val, AuthorizeType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static AuthorizeType get(String val) {
        return match(val, null);
    }

    public boolean eq(AuthorizeType val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "MENU,RESOURCE", example = "MENU")
    public String getCode() {
        return this.name();
    }

}
