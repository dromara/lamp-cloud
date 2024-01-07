package top.tangyh.lamp.system.vo.query.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 资源
 * </p>
 *
 * @author zuihou
 * @since 2021-09-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "资源")
public class DefResourcePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码;唯一编码，用于区分资源
     */
    @Schema(description = "编码")
    private String code;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 类型;[10-应用 20-菜单 30-视图 40-按钮 50-字段 06-数据]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.RESOURCE_TYPE)
     */
    @Schema(description = "类型")
    private String resourceType;
    /**
     * 父级ID
     */
    @Schema(description = "父级ID")
    private Long parentId;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String describe;
    /**
     * 地址栏路径;用于resource_type=菜单和视图
     */
    @Schema(description = "地址栏路径")
    private String path;
    /**
     * 页面路径;用于resource_type=菜单和视图
     */
    @Schema(description = "页面路径")
    private String component;
    /**
     * 重定向;用于resource_type=菜单和视图
     */
    @Schema(description = "重定向")
    private String redirect;
    /**
     * 图标
     */
    @Schema(description = "图标")
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
     * 排序;默认升序
     */
    @Schema(description = "排序")
    private Integer sortValue;
    /**
     * 分组
     */
    @Schema(description = "分组")
    private String subGroup;
    /**
     * 是否脱敏;显示时是否需要脱敏实现 (用于resource_type=字段)
     */
    @Schema(description = "是否脱敏")
    private Boolean isSecret;
    /**
     * 是否可以编辑;是否可以编辑(用于resource_type=字段)
     */
    @Schema(description = "是否可以编辑")
    private Boolean isEdit;
    /**
     * 控制器;控制器类名(用于resource_type=接口)
     */
    @Schema(description = "控制器")
    private String controller;
    /**
     * 服务名;取配置文件中 spring.application.name (用于resource_type=接口)
     */
    @Schema(description = "服务名")
    private String springApplicationName;
    /**
     * 请求类型;POST GET PUT DELETE (用于resource_type=接口)
     */
    @Schema(description = "请求类型")
    private String method;
    /**
     * 是否忽略KeepAlive缓存;用于resource_type=菜单和视图
     */
    @Schema(description = "是否忽略KeepAlive缓存")
    private Boolean ignoreKeepAlive;
    /**
     * 是否固定标签;用于resource_type=菜单和视图
     */
    @Schema(description = "是否固定标签")
    private Boolean affix;
    /**
     * 路由切换的动画名;用于resource_type=菜单和视图. [缩放淡入淡出：zoom-fade 缩小：zoom-out 淡入淡出：fade-slide 淡出底部：fade-bottom 导入淡出：fade-scale] @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.RESOURCE_TRANSITION_NAME)
     */
    @Schema(description = "路由切换的动画名")
    private String transitionName;
    /**
     * 隐藏该路由在面包屑上面的显示;用于resource_type=菜单和视图
     */
    @Schema(description = "隐藏该路由在面包屑上面的显示")
    private Boolean hideBreadcrumb;
    /**
     * 隐藏标签页;true:该页面不在标签页显示。（用于resource_type=菜单和视图）
     */
    @Schema(description = "隐藏标签页")
    private Boolean hideTab;
    /**
     * 外链地址是否内嵌iframe;true：外链地址在内嵌iframe中打开。false：外链地址在新窗口打开。（用于resource_type=菜单和视图）
     */
    @Schema(description = "外链地址是否内嵌iframe")
    private Boolean isFrameSrc;

}
