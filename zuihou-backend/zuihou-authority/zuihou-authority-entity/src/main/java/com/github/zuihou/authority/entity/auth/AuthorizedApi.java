package com.github.zuihou.authority.entity.auth;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * API资源
 * 后端需要授权方可访问的api集合
 * </p>
 *
 * @author zuihou
 * @since 2019-06-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_authorized_api")
@ApiModel(value = "AuthorizedApi", description = "API资源 后端需要授权方可访问的api集合")
public class AuthorizedApi extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 服务id
     * #c_auth_micro_service
     */
    @ApiModelProperty(value = "服务id")
    @TableField("micro_service_id")
    private Long microServiceId;

    /**
     * 基础路径
     */
    @ApiModelProperty(value = "基础路径")
    @Length(max = 100, message = "基础路径长度不能超过100")
    @TableField("base_path")
    private String basePath;

    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @Length(max = 255, message = "接口名称长度不能超过255")
    @TableField("name_")
    private String name;

    /**
     * 接口描述
     */
    @ApiModelProperty(value = "接口描述")
    @Length(max = 255, message = "接口描述长度不能超过255")
    @TableField("description")
    private String description;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    @Length(max = 150, message = "地址长度不能超过150")
    @TableField("uri_")
    private String uri;

    /**
     * 请求方式
     * #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
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
    public AuthorizedApi(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                         Long microServiceId, String basePath, String name, String description, String uri,
                         HttpMethod httpMethod, Boolean deprecated, Boolean isCertification) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.microServiceId = microServiceId;
        this.basePath = basePath;
        this.name = name;
        this.description = description;
        this.uri = uri;
        this.httpMethod = httpMethod;
        this.deprecated = deprecated;
        this.isCertification = isCertification;
    }

}
