package com.github.zuihou.authority.entity.common;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * 字典项
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
@TableName("c_common_dictionary_item")
@ApiModel(value = "DictionaryItem", description = "字典项")
public class DictionaryItem extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 类型ID
     */
    @ApiModelProperty(value = "类型ID")
    @NotNull(message = "类型ID不能为空")
    @TableField("dictionary_id")
    private Long dictionaryId;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @NotEmpty(message = "类型不能为空")
    @Length(max = 64, message = "类型长度不能超过64")
    @TableField(value = "dictionary_type", condition = LIKE)
    @Excel(name = "类型", width = 20)
    private String dictionaryType;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Length(max = 64, message = "编码长度不能超过64")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "编码", width = 20)
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 64, message = "名称长度不能超过64")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "名称", width = 40)
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("status_")
    @Excel(name = "状态", replace = {"启用_true", "禁用_false", "_null"})
    private Boolean status;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Length(max = 255, message = "描述长度不能超过255")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "描述", width = 50)
    private String describe;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    @Excel(name = "排序", width = 10)
    private Integer sortValue;


    @Builder
    public DictionaryItem(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                          Long dictionaryId, String dictionaryType, String code, String name, Boolean status,
                          String describe, Integer sortValue) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.dictionaryId = dictionaryId;
        this.dictionaryType = dictionaryType;
        this.code = code;
        this.name = name;
        this.status = status;
        this.describe = describe;
        this.sortValue = sortValue;
    }

}
