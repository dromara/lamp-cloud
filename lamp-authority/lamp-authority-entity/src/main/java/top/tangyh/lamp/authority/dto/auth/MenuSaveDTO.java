package top.tangyh.lamp.authority.dto.auth;


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
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 菜单
 * </p>
 *
 * @author zuihou
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description="菜单")
public class MenuSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="名称")
    @NotEmpty(message = "请填写名称")
    @Size(max = 255, message = "名称长度不能超过255")
    protected String label;
    /**
     * 描述
     */
    @Schema(description="描述")
    @Size(max = 200, message = "描述长度不能超过200")
    private String describe;
    @Schema(description="上级资源")
    protected Long parentId;
    /**
     * 路径
     */
    @Schema(description="地址栏地址")
//    @NotEmpty(message = "请填写地址栏地址")
    @Size(max = 255, message = "地址栏地址长度不能超过255")
    private String path;
    /**
     * 组件
     */
    @Schema(description="页面代码地址")
//    @NotEmpty(message = "请填写页面代码地址")
    @Size(max = 255, message = "页面代码地址长度不能超过255")
    private String component;
    /**
     * 菜单图标
     */
    @Schema(description="图标")
    @Size(max = 255, message = "图标长度不能超过255")
    private String icon;
    /**
     * 分组
     */
    @Schema(description="分组")
    @Size(max = 20, message = "分组长度不能超过20")
    private String group;

    @Schema(description="排序号")
    protected Integer sortValue;

    /**
     * 通用菜单
     * True表示无需分配所有人就可以访问的
     */
    @Schema(description="通用菜单")
    private Boolean isGeneral;
    /**
     * 状态
     */
    @Schema(description="状态")
    private Boolean state;
    /**
     * 内置
     */
    @Schema(description="内置")
    private Boolean readonly;
    /**
     * 类型;[20-菜单 60-数据]
     */
    @Schema(description="类型")
    @Size(max = 2, message = "类型长度不能超过{max}")
    private String resourceType;
    /**
     * 数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]
     */
    @Schema(description="数据范围")
    @Size(max = 2, message = "数据范围长度不能超过{max}")
    private String dataScope;
    /**
     * 实现类;自定义实现类全类名
     */
    @Schema(description="实现类")
    @Size(max = 255, message = "实现类长度不能超过{max}")
    private String customClass;

    /**
     * 是否默认
     */
    @Schema(description="是否默认")
    private Boolean isDef;

}
