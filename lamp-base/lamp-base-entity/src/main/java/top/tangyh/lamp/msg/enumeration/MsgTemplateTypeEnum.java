package top.tangyh.lamp.msg.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 消息表
 * </p>
 *
 * @author zuihou
 * @date 2021-11-15
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "消息类型-枚举")
public enum MsgTemplateTypeEnum implements BaseEnum {

    /**
     * TO_DO="短信"
     */
    SMS("01", "短信"),
    /**
     * WARN="邮件"
     */
    MAIL("02", "邮件"),
    /**
     * NOTIFY="站内信"
     */
    NOTICE("03", "站内信"),
    ;

    private String value;
    @Schema(description = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static MsgTemplateTypeEnum match(String val, MsgTemplateTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static MsgTemplateTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(MsgTemplateTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "TO_DO,NOTICE,EARLY_WARNING", example = "TO_DO")
    public String getCode() {
        return this.value;
    }

}
