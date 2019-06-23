package com.github.zuihou.authority.entity.auth;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.authority.enumeration.auth.AuthorizeType;
import com.github.zuihou.base.entity.SuperEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实体类
 * 资源API分配
 * 资源中需要请求的api  并且此api会被鉴权，若不需要鉴权的api就不要加入到
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
@TableName("c_auth_authorized_api_resource")
@ApiModel(value = "AuthorizedApiResource", description = "资源API分配 资源中需要请求的api  并且此api会被鉴权，若不需要鉴权的api就不要加入到")
public class AuthorizedApiResource extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源ID
     * #c_auth_resource
     */
    @ApiModelProperty(value = "资源ID")
    @TableField("resource_id")
    private Long resourceId;

    /**
     * 接口资源ID
     * #c_auth_authorized_api
     */
    @ApiModelProperty(value = "接口资源ID")
    @TableField("authorized_api_id")
    private Long authorizedApiId;

    /**
     * 授权类型
     * #AuthorizeType{MANUAL:手动;AUTO:自动}
     */
    @ApiModelProperty(value = "授权类型")
    @TableField("authorize_type")
    private AuthorizeType authorizeType;


    @Builder
    public AuthorizedApiResource(Long id, LocalDateTime createTime, Long createUser,
                                 Long resourceId, Long authorizedApiId, AuthorizeType authorizeType) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.resourceId = resourceId;
        this.authorizedApiId = authorizedApiId;
        this.authorizeType = authorizeType;
    }

}
