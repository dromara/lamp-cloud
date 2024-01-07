package top.tangyh.lamp.system.enumeration.tenant;

import lombok.Getter;

/**
 * 应用授权枚举
 *
 * @author tangyh
 * @date 2021/3/15 3:34 下午
 */
@Getter
public enum ApplicationGrantTypeEnum {

    /**
     * 应用授权
     */
    GRANT("10", "应用授权"),

    /**
     * 应用续期
     */
    RENEWAL("20", "应用续期"),

    /**
     * 取消授权
     */
    CANCEL("30", "取消授权");

    /**
     * 资源类型
     */
    private final String type;
    private final String desc;

    ApplicationGrantTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
