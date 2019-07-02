package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
 * 服务表
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
@ApiModel(value = "MicroServiceUpdateDTO", description = "服务表")
public class MicroServiceUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 服务名称
     */
    @ApiModelProperty(value = "服务名称")
    @Length(max = 20, message = "服务名称长度不能超过20")
    private String name;
    /**
     * 服务描述
     */
    @ApiModelProperty(value = "服务描述")
    @Length(max = 100, message = "服务描述长度不能超过100")
    private String describe;
    /**
     * eureka编码
     * 就是服务注册到eureka后，他的application name
     */
    @ApiModelProperty(value = "eureka编码")
    @Length(max = 100, message = "eureka编码长度不能超过100")
    private String eurekaCode;
    /**
     * swagger地址
     */
    @ApiModelProperty(value = "swagger地址")
    @Length(max = 255, message = "swagger地址长度不能超过255")
    private String swaggerUrl;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortvalue;

}
