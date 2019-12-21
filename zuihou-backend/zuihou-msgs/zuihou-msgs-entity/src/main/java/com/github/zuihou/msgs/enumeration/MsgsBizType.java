package com.github.zuihou.msgs.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zuihou.base.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 消息中心表
 * </p>
 *
 * @author zuihou
 * @date 2019-12-21
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MsgsBizType", description = "业务类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MsgsBizType implements BaseEnum {

    /**
     * USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;
     */
    USER_LOCK("账号锁定"),
    USER_REG("账号申请"),
    WORK_APPROVAL("考勤审批"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static MsgsBizType match(String val, MsgsBizType def) {
        for (MsgsBizType enm : MsgsBizType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static MsgsBizType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return name().equalsIgnoreCase(val);
    }

    public boolean eq(MsgsBizType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "USER_LOCK", example = "USER_LOCK")
    public String getCode() {
        return name();
    }

}
