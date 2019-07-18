package com.github.zuihou.authority.entity.auth;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.authority.enumeration.auth.ResourceType;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.common.enums.HttpMethod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 资源
 * </p>
 *
 * @author zuihou
 * @since 2019-07-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_resource")
@ApiModel(value = "Resource", description = "资源")
public class Resource extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源编码
     * 规则：
     * 链接：
     * 数据列：
     * 按钮：
     */
    @ApiModelProperty(value = "资源编码")
    @Length(max = 255, message = "资源编码长度不能超过255")
    @TableField("code")
    private String code;

    /**
     * 资源类型
     * #ResourceType{BUTTON:按钮;URI:链接;COLUMN:数据列;}
     */
    @ApiModelProperty(value = "资源类型")
    @NotNull(message = "资源类型不能为空")
    @TableField("resource_type")
    private ResourceType resourceType;

    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @NotEmpty(message = "接口名称不能为空")
    @Length(max = 255, message = "接口名称长度不能超过255")
    @TableField("name")
    private String name;

    /**
     * 服务ID
     * #c_auth_micro_service
     */
    @ApiModelProperty(value = "服务ID")
    @TableField("micro_service_id")
    private Long microServiceId;

    /**
     * 菜单ID
     * #c_auth_menu
     */
    @ApiModelProperty(value = "菜单ID")
    @TableField("menu_id")
    private Long menuId;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    @Length(max = 255, message = "菜单名称长度不能超过255")
    @TableField("menu_name")
    private String menuName;

    /**
     * 类标签
     */
    @ApiModelProperty(value = "类标签")
    @Length(max = 255, message = "类标签长度不能超过255")
    @TableField("tags")
    private String tags;

    /**
     * 接口描述
     */
    @ApiModelProperty(value = "接口描述")
    @Length(max = 255, message = "接口描述长度不能超过255")
    @TableField("describe_")
    private String describe;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    @Length(max = 150, message = "地址长度不能超过150")
    @TableField("uri")
    private String uri;

    /**
     * 请求方式
     * #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     *
     */
    @ApiModelProperty(value = "请求方式")
    @TableField("http_method")
    private HttpMethod httpMethod;

    /**
     * 是否过时
     */
    @ApiModelProperty(value = "是否过时")
    @TableField("deprecated")
    private Boolean deprecated;


    @Builder
    public Resource(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                    String code, ResourceType resourceType, String name, Long microServiceId, Long menuId,
                    String menuName, String tags, String describe, String uri, HttpMethod httpMethod, Boolean deprecated) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.code = code;
        this.resourceType = resourceType;
        this.name = name;
        this.microServiceId = microServiceId;
        this.menuId = menuId;
        this.menuName = menuName;
        this.tags = tags;
        this.describe = describe;
        this.uri = uri;
        this.httpMethod = httpMethod;
        this.deprecated = deprecated;
    }

}
