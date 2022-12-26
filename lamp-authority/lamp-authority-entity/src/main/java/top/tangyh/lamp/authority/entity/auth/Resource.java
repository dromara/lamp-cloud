package top.tangyh.lamp.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import top.tangyh.basic.base.entity.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 资源
 * </p>
 *
 * @author zuihou
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_resource")
@Schema(description="资源")
@AllArgsConstructor
public class Resource extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @Schema(description="编码")
    @Size(max = 500, message = "编码长度不能超过500")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "编码")
    private String code;

    /**
     * 名称
     */
    @Schema(description="名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "名称")
    private String name;

    /**
     * 菜单ID
     * #c_menu
     */
    @Schema(description="菜单ID")
    @TableField("menu_id")
    @Excel(name = "菜单ID")
    private Long menuId;

    /**
     * 描述
     */
    @Schema(description="描述")
    @Size(max = 255, message = "描述长度不能超过255")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "描述")
    private String describe;

    /**
     * 内置
     */
    @Schema(description="内置")
    @TableField("readonly_")
    @Excel(name = "内置", replace = {"是_true", "否_false", "_null"})
    private Boolean readonly;


    @Builder
    public Resource(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                    String code, String name, Long menuId, String describe, Boolean readonly) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.code = code;
        this.name = name;
        this.menuId = menuId;
        this.describe = describe;
        this.readonly = readonly;
    }

}
