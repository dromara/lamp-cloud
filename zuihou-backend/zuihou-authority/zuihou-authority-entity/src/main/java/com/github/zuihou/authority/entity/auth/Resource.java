package com.github.zuihou.authority.entity.auth;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.authority.enumeration.auth.MenuType;
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
 * @since 2019-06-26
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
     */
    @ApiModelProperty(value = "资源编码")
    @Length(max = 255, message = "资源编码长度不能超过255")
    @TableField("code")
    private String code;

    /**
     * 资源类型 
     * #ResourceType{BUTTON:按钮;URI:链接;}
     */
    @ApiModelProperty(value = "资源类型")
    @TableField("type")
    private MenuType type;

    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @Length(max = 255, message = "接口名称长度不能超过255")
    @TableField("name")
    private String name;

    /**
     * 菜单id
     * #c_auth_menu
     */
    @ApiModelProperty(value = "菜单id")
    @TableField("menu_id")
    private Long menuId;

    /**
     * 基础路径
     */
    @ApiModelProperty(value = "基础路径")
    @Length(max = 100, message = "基础路径长度不能超过100")
    @TableField("base_path")
    private String basePath;

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

    /**
     * 是否需要认证
     */
    @ApiModelProperty(value = "是否需要认证")
    @TableField("is_certification")
    private Boolean isCertification;


    @Builder
    public Resource(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                    String code, MenuType type, String name, Long menuId, String basePath,
                    String describe, String uri, HttpMethod httpMethod, Boolean deprecated, Boolean isCertification) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.code = code;
        this.type = type;
        this.name = name;
        this.menuId = menuId;
        this.basePath = basePath;
        this.describe = describe;
        this.uri = uri;
        this.httpMethod = httpMethod;
        this.deprecated = deprecated;
        this.isCertification = isCertification;
    }

}
