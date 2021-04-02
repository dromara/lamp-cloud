package com.tangyh.lamp.authority.entity.core;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.annotation.echo.Echo;
import com.tangyh.basic.base.entity.TreeEntity;
import com.tangyh.basic.model.EchoVO;
import com.tangyh.lamp.common.constant.DictionaryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.tangyh.lamp.common.constant.EchoConstants.DICTIONARY_ITEM_CLASS;
import static com.tangyh.lamp.common.constant.EchoConstants.FIND_NAME_BY_IDS;

/**
 * <p>
 * 实体类
 * 组织
 * </p>
 *
 * @author zuihou
 * @since 2021-04-01
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_org")
@ApiModel(value = "Org", description = "组织")
@AllArgsConstructor
public class Org extends TreeEntity<Org, Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = new HashMap<>();
    /**
     * 类型
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.ORG_TYPE)
     */
    @ApiModelProperty(value = "类型")
    @Size(max = 2, message = "类型长度不能超过2")
    @TableField(value = "type_", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.ORG_TYPE)
    @Excel(name = "类型")
    private String type;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    @Size(max = 255, message = "简称长度不能超过255")
    @TableField(value = "abbreviation", condition = LIKE)
    @Excel(name = "简称")
    private String abbreviation;

    /**
     * 树结构
     */
    @ApiModelProperty(value = "树结构")
    @Size(max = 255, message = "树结构长度不能超过255")
    @TableField(value = "tree_path", condition = LIKE)
    @Excel(name = "树结构")
    private String treePath;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    @Excel(name = "状态", replace = {"是_true", "否_false", "_null"})
    private Boolean state;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 255, message = "描述长度不能超过255")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "描述")
    private String describe;


    @Builder
    public Org(Long id, String label, Long parentId, Integer sortValue, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
               String type, String abbreviation, String treePath, Boolean state, String describe) {
        this.id = id;
        this.label = label;
        this.parentId = parentId;
        this.sortValue = sortValue;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.type = type;
        this.abbreviation = abbreviation;
        this.treePath = treePath;
        this.state = state;
        this.describe = describe;
    }

}
