package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import com.github.zuihou.authority.entity.auth.Resource;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实体类
 * 资源
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
@ApiModel(value = "ResourceDTO", description = "资源")
public class ResourceDTO extends Resource implements Serializable {

    /**
     * 在DTO中新增并自定义字段，需要覆盖验证的字段，请新建DTO。Entity中的验证规则可以自行修改，但下次生成代码时，记得同步代码！！
     */
    private static final long serialVersionUID = 1L;

    public static ResourceDTO build() {
        return new ResourceDTO();
    }

}
