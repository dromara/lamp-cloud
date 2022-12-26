package top.tangyh.lamp.model.enumeration.system;

import top.tangyh.basic.interfaces.BaseEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 企业
 * </p>
 *
 * @author zuihou
 * @date 2020-11-19
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="连接类型-枚举")
public enum TenantConnectTypeEnum implements BaseEnum {

    /**
     * SYSTEM="系统"
     */
    SYSTEM("系统"),
    /**
     * CUSTOM="自定义"
     */
    CUSTOM("自定义"),
    ;

    @Schema(description="描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static TenantConnectTypeEnum match(String val, TenantConnectTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static TenantConnectTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(TenantConnectTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description="编码", allowableValues = "LOCAL,REMOTE", example = "LOCAL")
    public String getCode() {
        return this.name();
    }

}
