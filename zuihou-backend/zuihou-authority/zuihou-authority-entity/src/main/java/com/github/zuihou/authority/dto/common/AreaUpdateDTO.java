package com.github.zuihou.authority.dto.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.common.constant.InjectionFieldConstants;
import com.github.zuihou.injection.annonation.InjectionField;
import com.github.zuihou.model.RemoteData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 地区表
 * </p>
 *
 * @author zuihou
 * @since 2020-01-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "AreaUpdateDTO", description = "地区表")
public class AreaUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 255, message = "名称长度不能超过255")
    private String label;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Length(max = 64, message = "编码长度不能超过64")
    private String code;
    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    @Length(max = 255, message = "全名长度不能超过255")
    private String fullName;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @Length(max = 255, message = "经度长度不能超过255")
    private String longitude;
    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    @Length(max = 255, message = "维度长度不能超过255")
    private String latitude;

    @ApiModelProperty(value = "数据来源")
    @Length(max = 255, message = "数据来源长度不能超过255")
    @TableField(value = "source_", condition = LIKE)
    private String source;

    /**
     * 行政区级
     */
    @ApiModelProperty(value = "行政区级")
    @NotNull(message = "行政区级不能为空")
    @TableField("level")
    @InjectionField(api = InjectionFieldConstants.DICTIONARY_ITEM_CLASS, method = InjectionFieldConstants.DICTIONARY_ITEM_METHOD)
    private RemoteData<String, String> level;

    /**
     * 父CODE
     */
    @ApiModelProperty(value = "父CODE")
    @Length(max = 64, message = "父CODE长度不能超过64")
    private String parentCode;
    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    private Long parentId;

}
