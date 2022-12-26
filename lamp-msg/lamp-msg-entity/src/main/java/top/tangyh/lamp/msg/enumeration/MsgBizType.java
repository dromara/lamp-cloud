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
 * @date 2020-11-21
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "业务类型-枚举")
public enum MsgBizType implements BaseEnum {

    /**
     * USER_LOCK="账号锁定"
     */
    USER_LOCK("账号锁定"),
    /**
     * USER_REG="账号申请"
     */
    USER_REG("账号申请"),
    /**
     * WORK_APPROVAL="考勤审批"
     */
    WORK_APPROVAL("考勤审批"),
    ;

    @Schema(description = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static MsgBizType match(String val, MsgBizType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static MsgBizType get(String val) {
        return match(val, null);
    }

    public boolean eq(MsgBizType val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "USER_LOCK,USER_REG,WORK_APPROVAL", example = "USER_LOCK")
    public String getCode() {
        return this.name();
    }

}
