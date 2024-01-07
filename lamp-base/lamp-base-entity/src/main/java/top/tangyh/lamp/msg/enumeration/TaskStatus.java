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
 * 消息
 * </p>
 *
 * @author zuihou
 * @date 2022-07-10 11:41:17
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "执行状态-枚举")
public enum TaskStatus implements BaseEnum {

    /**
     * DRAFT
     */
    DRAFT("草稿"),
    /**
     * WAITING
     */
    WAITING("等待执行"),
    /**
     * SUCCESS
     */
    SUCCESS("执行成功"),
    /**
     * FAIL
     */
    FAIL("执行失败"),
    ;

    @Schema(description = "描述")
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static TaskStatus match(String val, TaskStatus def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static TaskStatus get(String val) {
        return match(val, null);
    }

    public boolean eq(TaskStatus val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "name", allowableValues = "DRAFT,WAITING,SUCCESS,FAIL", example = "DRAFT")
    public String getCode() {
        return this.name();
    }

    @Override
    @Schema(description = "数据库中的值")
    public String getValue() {
        return this.name();
    }

}
