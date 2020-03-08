package com.github.zuihou.authority.dto.auth;

import com.github.zuihou.database.mybatis.auth.DataScopeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 角色
 * </p>
 *
 * @author zuihou
 * @since 2019-11-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "RoleSaveDTO", description = "角色")
public class RolePageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @Length(max = 30, message = "角色名称长度不能超过30")
    private String name;
    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String code;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String describe;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;
    /**
     * 是否内置角色
     */
    @ApiModelProperty(value = "是否内置角色")
    private Boolean readonly;
    /**
     * 数据权限类型
     * #DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}
     */
    @ApiModelProperty(value = "数据权限类型")
    private DataScopeType dsType;
    @ApiModelProperty(value = "开始创建时间")
    private LocalDateTime startCreateTime;
    @ApiModelProperty(value = "结束创建时间")
    private LocalDateTime endCreateTime;
}
