package top.tangyh.lamp.model.entity.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.basic.base.entity.TreeEntity;
import top.tangyh.lamp.model.constant.EchoDictType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;
import static top.tangyh.lamp.model.constant.Condition.LIKE;
import static top.tangyh.lamp.model.constant.EchoApi.DICTIONARY_ITEM_FEIGN_CLASS;

/**
 * <p>
 * 实体类
 * 资源
 * </p>
 *
 * @author zuihou
 * @since 2021-09-15
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_resource")
@AllArgsConstructor
@Builder
public class SysResource extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @Schema(description="编码")
    @Size(max = 500, message = "编码长度不能超过500")
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 名称
     */
    @Schema(description="名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 菜单ID
     * #c_menu
     */
    @Schema(description="菜单ID")
    @TableField("menu_id")
    private Long menuId;

    /**
     * 描述
     */
    @Schema(description="描述")
    @Size(max = 255, message = "描述长度不能超过255")
    @TableField(value = "describe_", condition = LIKE)
    private String describe;

    /**
     * 内置
     */
    @Schema(description="内置")
    @TableField("readonly_")
    private Boolean readonly;


}
