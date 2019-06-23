package com.github.zuihou.authority.entity.common;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 字典目录
 * </p>
 *
 * @author zuihou
 * @since 2019-06-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_common_dictionary")
@ApiModel(value = "Dictionary", description = "字典目录")
public class Dictionary extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     * 一颗树仅仅有一个统一的编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Length(max = 64, message = "编码长度不能超过64")
    @TableField("code_")
    private String code;

    /**
     * 父级id
     * 顶级的字典父级id是自己
     */
    @ApiModelProperty(value = "父级id")
    @NotNull(message = "父级id不能为空")
    @TableField("parent_id")
    private Long parentId;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    @NotEmpty(message = "字典名称不能为空")
    @Length(max = 64, message = "字典名称长度不能超过64")
    @TableField("name_")
    private String name;

    /**
     * 字典描述
     */
    @ApiModelProperty(value = "字典描述")
    @Length(max = 200, message = "字典描述长度不能超过200")
    @TableField("desc_")
    private String desc;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    @NotNull(message = "是否启用不能为空")
    @TableField("enable_is")
    private Boolean enableIs;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    @NotNull(message = "是否删除不能为空")
    @TableField("delete_is")
    private Boolean deleteIs;


    @Builder
    public Dictionary(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                      String code, Long parentId, String name, String desc, Boolean enableIs, Boolean deleteIs) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.code = code;
        this.parentId = parentId;
        this.name = name;
        this.desc = desc;
        this.enableIs = enableIs;
        this.deleteIs = deleteIs;
    }

}
