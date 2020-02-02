package com.github.zuihou.authority.dto.common;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.github.zuihou.model.RemoteData;
import com.github.zuihou.injection.annonation.InjectionField;
import com.github.zuihou.base.entity.TreeEntity;
import static com.github.zuihou.common.constant.InjectionFieldConstants.DICTIONARY_ITEM_METHOD;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import static com.github.zuihou.common.constant.InjectionFieldConstants.DICTIONARY_ITEM_CLASS;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 地区表
 * </p>
 *
 * @author zuihou
 * @since 2020-02-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "AreaPageDTO", description = "地区表")
public class AreaPageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    /**
     * 行政区级
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     * 
     * 
     */
    @ApiModelProperty(value = "行政区级")
    @Length(max = 10, message = "行政区级长度不能超过10")
    private String level;
    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    @Length(max = 255, message = "数据来源长度不能超过255")
    private String source;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startCreateTime;

    @ApiModelProperty(value = "截止时间")
    private LocalDateTime endCreateTime;

    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 255, message = "名称长度不能超过255")
    protected String label;

    @ApiModelProperty(value = "父ID")
    protected Long parentId;

    @ApiModelProperty(value = "排序号")
    protected Integer sortValue;
}
