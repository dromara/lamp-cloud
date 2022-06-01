package top.tangyh.lamp.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否
 *
 * @author tangyh
 * @date 2021/4/16 11:26 上午
 */
@Getter
@AllArgsConstructor
public enum StateEnum {
    ENABLE(true, 1, "1", "启用"),
    DISABLE(false, 0, "0", "禁用"),
    ;
    private Boolean bool;
    private int integer;
    private String str;
    private String describe;

    public static StateEnum match(String val, StateEnum... defs) {
        StateEnum def = defs.length > 0 ? defs[0] : DISABLE;
        if (val == null) {
            return def;
        }

        for (StateEnum value : StateEnum.values()) {
            if (value.getStr().equals(val)) {
                return value;
            }
        }
        return def;
    }

    public boolean eq(Integer val) {
        if (val == null) {
            return DISABLE.getBool();
        }
        return val.equals(this.getInteger());
    }

    public boolean eq(String val) {
        if (val == null) {
            return DISABLE.getBool();
        }
        return val.equals(this.getStr());
    }

    public boolean eq(Boolean val) {
        if (val == null) {
            return DISABLE.getBool();
        }
        return val.equals(this.getBool());
    }
}
