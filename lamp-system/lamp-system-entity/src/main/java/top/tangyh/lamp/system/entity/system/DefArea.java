package top.tangyh.lamp.system.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.TreeEntity;

import java.time.LocalDateTime;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 地区表
 * </p>
 *
 * @author zuihou
 * @since 2021-10-15
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@TableName("def_area")
@AllArgsConstructor
public class DefArea extends TreeEntity<DefArea, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @TableField(value = "code", condition = LIKE)
    private String code;
    /**
     * 城乡划分代码
     */
    @TableField(value = "division_code", condition = LIKE)
    private String divisionCode;

    /**
     * 全名
     */
    @TableField(value = "full_name", condition = LIKE)
    private String fullName;

    /**
     * 经度
     */
    @TableField(value = "longitude", condition = LIKE)
    private String longitude;

    /**
     * 维度
     */
    @TableField(value = "latitude", condition = LIKE)
    private String latitude;

    /**
     * 行政级别;[10-国家 20-省份/直辖市 30-地市 40-区县 50-乡镇]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.AREA_LEVEL)
     */
    @TableField(value = "level_", condition = LIKE)
    private String level;

    /**
     * 数据来源;[10-爬取 20-新增]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.AREA_SOURCE)
     */
    @TableField(value = "source_", condition = LIKE)
    private String source;

    /**
     * 状态
     */
    @TableField(value = "state")
    private Boolean state;

    /**
     * 树层级
     */
    @TableField(value = "tree_grade")
    private Integer treeGrade;

    /**
     * 树路径
     */
    @TableField(value = "tree_path", condition = LIKE)
    private String treePath;

    @TableField(value = "name", condition = LIKE)
    private String name;


    @Builder
    public DefArea(Long id, String name, Integer sortValue, Long parentId, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                   String code, String divisionCode, String fullName, String longitude, String latitude, String level,
                   String source, Boolean state, Integer treeGrade, String treePath) {
        this.id = id;
        this.name = name;
        this.sortValue = sortValue;
        this.parentId = parentId;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.code = code;
        this.fullName = fullName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.level = level;
        this.source = source;
        this.state = state;
        this.treeGrade = treeGrade;
        this.treePath = treePath;
        this.divisionCode = divisionCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefArea defArea = (DefArea) o;
        return Objects.equal(id, defArea.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
