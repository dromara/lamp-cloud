package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

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
 * @since 2019-07-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "ApplicationSaveDTO", description = "应用")
public class ApplicationSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 首页访问地址
     */
    @ApiModelProperty(value = "首页访问地址")
    @Length(max = 100, message = "首页访问地址长度不能超过100")
    private String indexUrl;
    /**
     * 应用程序的完整根路径
     */
    @ApiModelProperty(value = "应用程序的完整根路径")
    @Length(max = 80, message = "应用程序的完整根路径长度不能超过80")
    private String fullRootPath;
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
    private Integer sortvalue;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private Boolean isEnable;
    /**
     * ICP备案号
     */
    @ApiModelProperty(value = "ICP备案号")
    @Length(max = 32, message = "ICP备案号长度不能超过32")
    private String icpCode;
    /**
     * 标题logo
     */
    @ApiModelProperty(value = "标题logo")
    @Length(max = 255, message = "标题logo长度不能超过255")
    private String titleIcon;
    /**
     * 技术支持单位
     */
    @ApiModelProperty(value = "技术支持单位")
    @Length(max = 32, message = "技术支持单位长度不能超过32")
    private String supportUnit;
    /**
     * 公网备案号
     */
    @ApiModelProperty(value = "公网备案号")
    @Length(max = 32, message = "公网备案号长度不能超过32")
    private String commonRecord;

}
