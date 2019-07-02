package com.github.zuihou.authority.entity.core;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.zuihou.base.entity.Entity;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "CAuthMicroService", description = "服务表")
public class CAuthMicroService extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 服务名称
     */
    @ApiModelProperty(value = "服务名称")
    @Length(max = 20, message = "服务名称长度不能超过20")
    @TableField("name")
    private String name;

    /**
     * 服务描述
     */
    @ApiModelProperty(value = "服务描述")
    @Length(max = 100, message = "服务描述长度不能超过100")
    @TableField("describe_")
    private String describe;

    /**
     * eureka编码
     * 就是服务注册到eureka后，他的application name
     */
    @ApiModelProperty(value = "eureka编码")
    @Length(max = 100, message = "eureka编码长度不能超过100")
    @TableField("eureka_code")
    private String eurekaCode;

    /**
     * swagger地址
     */
    @ApiModelProperty(value = "swagger地址")
    @Length(max = 255, message = "swagger地址长度不能超过255")
    @TableField("swagger_url")
    private String swaggerUrl;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("sortvalue")
    private Integer sortvalue;


    @Builder
    public CAuthMicroService(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                             String name, String describe, String eurekaCode, String swaggerUrl, Integer sortvalue) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.name = name;
        this.describe = describe;
        this.eurekaCode = eurekaCode;
        this.swaggerUrl = swaggerUrl;
        this.sortvalue = sortvalue;
    }

}
