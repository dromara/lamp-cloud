package top.tangyh.lamp.datascope.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.TreeEntity;

import java.time.LocalDateTime;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 组织
 * </p>
 *
 * @author zuihou
 * @since 2021-10-21
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_org")
@AllArgsConstructor
public class BaseOrgBO extends TreeEntity<BaseOrgBO, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 类型;[10-单位 20-部门]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.ORG_TYPE)
     */
    @TableField(value = "type_", condition = LIKE)
    private String type;

    /**
     * 简称
     */
    @TableField(value = "short_name", condition = LIKE)
    private String shortName;

    /**
     * 父ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 树层级
     */
    @TableField(value = "tree_grade")
    private Integer treeGrade;

    /**
     * 树路径;用id拼接树结构
     */
    @TableField(value = "tree_path", condition = LIKE)
    private String treePath;

    /**
     * 排序
     */
    @TableField(value = "sort_value")
    private Integer sortValue;

    /**
     * 状态;[0-禁用 1-启用]
     */
    @TableField(value = "state")
    private Boolean state;

    /**
     * 备注
     */
    @TableField(value = "remarks", condition = LIKE)
    private String remarks;


    @Builder
    public BaseOrgBO(Long id, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                     String name, String type, String shortName, Long parentId, Integer treeGrade,
                     String treePath, Integer sortValue, Boolean state, String remarks) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.name = name;
        this.type = type;
        this.shortName = shortName;
        this.parentId = parentId;
        this.treeGrade = treeGrade;
        this.treePath = treePath;
        this.sortValue = sortValue;
        this.state = state;
        this.remarks = remarks;
    }

}
