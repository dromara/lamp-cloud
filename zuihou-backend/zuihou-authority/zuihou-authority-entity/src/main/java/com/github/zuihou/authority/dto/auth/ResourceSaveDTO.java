package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import com.github.zuihou.authority.enumeration.auth.ResourceType;
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
 * @since 2019-07-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "ResourceSaveDTO", description = "资源")
public class ResourceSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源编码
     */
    @ApiModelProperty(value = "资源编码")
    @Length(max = 255, message = "资源编码长度不能超过255")
    private String code;
    /**
     * 资源类型
     * #ResourceType{BUTTON:按钮;URI:链接;}
     */
    @ApiModelProperty(value = "资源类型")
    private ResourceType resourceType;
    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @Length(max = 255, message = "接口名称长度不能超过255")
    private String name;
    /**
     * 菜单id
     * #c_auth_menu
     */
    @ApiModelProperty(value = "菜单id")
    private Long menuId;
    /**
     * 基础路径
     */
    @ApiModelProperty(value = "基础路径")
    @Length(max = 100, message = "基础路径长度不能超过100")
    private String basePath;
    /**
     * 接口描述
     */
    @ApiModelProperty(value = "接口描述")
    @Length(max = 255, message = "接口描述长度不能超过255")
    private String describe;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    @Length(max = 150, message = "地址长度不能超过150")
    private String uri;
    /**
     * 请求方式
     * #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    @ApiModelProperty(value = "请求方式")
    private HttpMethod httpMethod;
    /**
     * 是否过时
     */
    @ApiModelProperty(value = "是否过时")
    private Boolean deprecated;
    /**
     * 是否需要认证
     */
    @ApiModelProperty(value = "是否需要认证")
    private Boolean isCertification;

}
