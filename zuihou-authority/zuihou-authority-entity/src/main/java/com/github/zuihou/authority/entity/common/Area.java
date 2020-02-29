package com.github.zuihou.authority.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.TreeEntity;
import com.github.zuihou.injection.annonation.InjectionField;
import com.github.zuihou.model.RemoteData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.github.zuihou.common.constant.InjectionFieldConstants.DICTIONARY_ITEM_CLASS;
import static com.github.zuihou.common.constant.InjectionFieldConstants.DICTIONARY_ITEM_METHOD;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_common_area")
@ApiModel(value = "Area", description = "地区表")
@AllArgsConstructor
public class Area extends TreeEntity<Area, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Length(max = 64, message = "编码长度不能超过64")
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    @Length(max = 255, message = "全名长度不能超过255")
    @TableField(value = "full_name", condition = LIKE)
    private String fullName;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @Length(max = 255, message = "经度长度不能超过255")
    @TableField(value = "longitude", condition = LIKE)
    private String longitude;

    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    @Length(max = 255, message = "维度长度不能超过255")
    @TableField(value = "latitude", condition = LIKE)
    private String latitude;

    /**
     * 行政区级
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "行政区级")
    @Length(max = 10, message = "行政区级长度不能超过10")
    @TableField(value = "level", condition = LIKE)
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD)
    private RemoteData<String, String> level;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    @Length(max = 255, message = "数据来源长度不能超过255")
    @TableField(value = "source_", condition = LIKE)
    private String source;


    @Builder
    public Area(Long id, String label, Integer sortValue, Long parentId, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                String code, String fullName, String longitude, String latitude, RemoteData<String, String> level, String source) {
        this.id = id;
        this.label = label;
        this.sortValue = sortValue;
        this.parentId = parentId;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.code = code;
        this.fullName = fullName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.level = level;
        this.source = source;
    }

}
