package top.tangyh.lamp.model.enumeration.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * 数据类型
 * 必须和数据字典【EchoDictType.Global.DATA_TYPE】 保持一致
 *
 * @author tangyh
 * @date 2021/3/15 3:34 下午
 */
@Getter
@Schema(description = "数据类型-枚举")
public enum DataTypeEnum implements BaseEnum {

    /**
     * 应用授权
     */
    SYSTEM("10", "系统值"),

    /**
     * 应用续期
     */
    BUSINESS("20", "业务值"),
    ;

    private final String code;
    private final String desc;

    DataTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据当前枚举的name匹配
     */
    public static DataTypeEnum match(String val, DataTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static DataTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(DataTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "10,20", example = "20")
    public String getCode() {
        return this.code;
    }

}
