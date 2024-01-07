package top.tangyh.lamp.generator.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.tangyh.basic.interfaces.BaseEnum;

/**
 * 项目类型
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/4/2 8:12 PM
 * @create [2022/4/2 8:12 PM ] [tangyh] [初始创建]
 */
@Getter
@AllArgsConstructor
public enum ProjectTypeEnum implements BaseEnum {
    /**
     * 单体版
     */
    BOOT("单体版"),
    /**
     * 微服务版
     */
    CLOUD("微服务版"),
    ;

    private final String desc;

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getValue() {
        return this.name();
    }

    public boolean eq(ProjectTypeEnum val) {
        return val != null && this.getCode().equalsIgnoreCase(val.getCode());
    }
}
