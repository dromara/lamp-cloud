package top.tangyh.lamp.system.vo.update.application;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.lamp.system.vo.save.application.DefResourceApiSaveVO;

import java.io.Serializable;
import java.util.List;

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
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "资源")
public class DefResourceUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "名称")
    @NotEmpty(message = "请填写名称")
    @Size(max = 255, message = "名称长度不能超过255")
    protected String name;
    @Schema(description = "父ID")
    protected Long parentId;
    @Schema(description = "排序号")
    protected Integer sortValue;
    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = SuperEntity.Update.class)
    private Long id;
    /**
     * 应用ID;#def_application
     */
    @Schema(description = "应用ID")
    @NotNull(message = "请填写应用ID")
    private Long applicationId;
    /**
     * 编码;唯一编码，用于区分资源
     */
    @Schema(description = "编码")
    @NotEmpty(message = "请填写编码")
    @Size(max = 255, message = "编码长度不能超过255")
    private String code;
    /**
     * 类型;[20-菜单 30-视图 40-按钮 50-字段 06-数据]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.RESOURCE_TYPE)
     * 菜单即左侧显示的菜单，视图即隐藏的菜单(需要配置在路由中)
     */
    @Schema(description = "类型")
    @NotEmpty(message = "请填写类型")
    @Size(max = 2, message = "类型长度不能超过2")
    private String resourceType;
    /**
     * 打开方式 [01-组件 02-内链 03-外链]
     */
    @Schema(description = "打开方式")
    @Size(max = 2, message = "打开方式长度不能超过2")
    private String openWith;
    /**
     * 描述;resource_type=接口时表示接口说明
     */
    @Schema(description = "描述")
    @Size(max = 255, message = "描述长度不能超过255")
    private String describe;
    /**
     * 地址栏路径;用于resource_type=菜单和视图和接口.resource_type=菜单和视图，表示地址栏地址, http开头表示外链, is_frame_src 为true表示在框架类打开.resource_type=接口，表示后端接口请求地址.
     */
    @Schema(description = "地址栏路径")
    @Size(max = 255, message = "路径长度不能超过255")
    private String path;
    /**
     * 页面路径;用于resource_type=菜单和视图.
     * 前端页面在src/views目录下的相对地址.
     */
    @Schema(description = "页面路径")
    @Size(max = 255, message = "组件长度不能超过255")
    private String component;
    /**
     * 重定向;用于resource_type=菜单和视图
     */
    @Schema(description = "重定向")
    @Size(max = 255, message = "重定向长度不能超过255")
    private String redirect;
    /**
     * 图标
     */
    @Schema(description = "图标")
    @Size(max = 255, message = "图标长度不能超过255")
    private String icon;
    /**
     * 是否公共资源;1-无需分配所有人就可以访问的
     */
    @Schema(description = "是否公共资源")
    private Boolean isGeneral;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    private Boolean state;
    /**
     * 分组
     */
    @Schema(description = "分组")
    @Size(max = 255, message = "分组长度不能超过255")
    private String subGroup;
    /**
     * 是否脱敏;显示时是否需要脱敏实现 (用于resource_type=字段)
     */
    @Schema(description = "是否脱敏")
    private Boolean fieldIsSecret;
    /**
     * 是否可以编辑;是否可以编辑(用于resource_type=字段)
     */
    @Schema(description = "是否可以编辑")
    private Boolean fieldIsEdit;
    /**
     * 数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]
     */
    @Schema(description = "数据范围")
    private String dataScope;
    /**
     * 实现类;自定义实现类全类名
     */
    @Schema(description = "实现类")
    private String customClass;
    /**
     * 是否默认
     */
    @Schema(description = "是否默认")
    private Boolean isDef;
    /**
     * 元数据;菜单视图的元数据
     */
    @Schema(description = "元数据")
    @Size(max = 512, message = "元数据长度不能超过512")
    private String metaJson;
    @Schema(description = "资源关联的接口")
    @Valid
    private List<DefResourceApiSaveVO> resourceApiList;
}
