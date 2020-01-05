package com.github.zuihou.authority.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_common_area")
@ApiModel(value = "Area", description = "地区表")
public class Area extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 255, message = "名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    private String name;

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
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    private Integer sortValue;

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
     */
    @ApiModelProperty(value = "行政区级")
    @NotNull(message = "行政区级不能为空")
    @TableField("level")
    private Integer level;

    /**
     * 父CODE
     */
    @ApiModelProperty(value = "父CODE")
    @Length(max = 64, message = "父CODE长度不能超过64")
    @TableField(value = "parent_code", condition = LIKE)
    private String parentCode;

    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    @TableField("parent_id")
    private Long parentId;


    @Builder
    public Area(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                String name, String code, String fullName, Integer sortValue, String longitude,
                String latitude, Integer level, String parentCode, Long parentId) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.name = name;
        this.code = code;
        this.fullName = fullName;
        this.sortValue = sortValue;
        this.longitude = longitude;
        this.latitude = latitude;
        this.level = level;
        this.parentCode = parentCode;
        this.parentId = parentId;
    }

}
