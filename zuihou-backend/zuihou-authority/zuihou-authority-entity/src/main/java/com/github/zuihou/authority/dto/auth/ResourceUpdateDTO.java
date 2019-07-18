package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.zuihou.authority.enumeration.auth.ResourceType;
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
@ApiModel(value = "ResourceUpdateDTO", description = "资源")
public class ResourceUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

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
    @NotNull(message = "资源类型不能为空")
    private ResourceType resourceType;
    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @Length(max = 255, message = "接口名称长度不能超过255")
    @NotEmpty(message = "接口名称不能为空")
    private String name;


    /**
     * 服务ID
     * #c_auth_micro_service
     */
    @ApiModelProperty(value = "服务ID")
    private Long microServiceId;
    /**
     * 菜单id
     * #c_auth_menu
     */
    @NotNull(message = "菜单不能为空")
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @NotEmpty(message = "菜单不能为空")
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

}
