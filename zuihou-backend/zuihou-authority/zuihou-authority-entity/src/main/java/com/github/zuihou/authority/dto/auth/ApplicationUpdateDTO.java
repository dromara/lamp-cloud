package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
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
 * 应用
 * </p>
 *
 * @author zuihou
 * @since 2019-12-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "ApplicationUpdateDTO", description = "应用")
public class ApplicationUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * AppId
     */
    @ApiModelProperty(value = "AppId")
    @Length(max = 100, message = "AppId长度不能超过100")
    private String appKey;
    /**
     * AppSecret
     */
    @ApiModelProperty(value = "AppSecret")
    @Length(max = 255, message = "AppSecret长度不能超过255")
    private String appSecret;
    /**
     * 首页地址
     */
    @ApiModelProperty(value = "首页地址")
    @Length(max = 100, message = "首页地址长度不能超过100")
    private String indexUrl;
    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    @Length(max = 20, message = "应用名称长度不能超过20")
    private String name;
    /**
     * 应用logo
     */
    @ApiModelProperty(value = "应用logo")
    @Length(max = 255, message = "应用logo长度不能超过255")
    private String logoUrl;
    /**
     * 功能描述
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 200, message = "功能描述长度不能超过200")
    private String describe;
    /**
     * 应用编码
     * 必须唯一
     */
    @ApiModelProperty(value = "应用编码")
    @NotEmpty(message = "应用编码不能为空")
    @Length(max = 20, message = "应用编码长度不能超过20")
    private String code;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer sortValue;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private Boolean isEnable;

}
