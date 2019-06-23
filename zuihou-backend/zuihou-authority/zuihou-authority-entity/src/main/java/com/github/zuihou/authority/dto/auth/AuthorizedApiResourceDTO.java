package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import com.github.zuihou.authority.entity.auth.AuthorizedApiResource;

import io.swagger.annotations.ApiModel;
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
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AuthorizedApiResourceDTO", description = "资源API分配 资源中需要请求的api  并且此api会被鉴权，若不需要鉴权的api就不要加入到")
public class AuthorizedApiResourceDTO extends AuthorizedApiResource implements Serializable {

    /**
     * 在DTO中新增并自定义字段，需要覆盖验证的字段，请新建DTO。Entity中的验证规则可以自行修改，但下次生成代码时，记得同步代码！！
     */
    private static final long serialVersionUID = 1L;

    public static AuthorizedApiResourceDTO build() {
        return new AuthorizedApiResourceDTO();
    }

}
