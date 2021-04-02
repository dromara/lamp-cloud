package com.tangyh.lamp.authority.entity.common;

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

import javax.validation.constraints.NotEmpty;
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
 * 地区表
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
@TableName("c_area")
@ApiModel(value = "Area", description = "地区表")
@AllArgsConstructor
public class Area extends TreeEntity<Area, Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = new HashMap<>();
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "编码")
    private String code;

    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    @Size(max = 255, message = "全名长度不能超过255")
    @TableField(value = "full_name", condition = LIKE)
    @Excel(name = "全名")
    private String fullName;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @Size(max = 255, message = "经度长度不能超过255")
    @TableField(value = "longitude", condition = LIKE)
    @Excel(name = "经度")
    private String longitude;

    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    @Size(max = 255, message = "维度长度不能超过255")
    @TableField(value = "latitude", condition = LIKE)
    @Excel(name = "维度")
    private String latitude;

    /**
     * 行政区级
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.AREA_LEVEL)
     */
    @ApiModelProperty(value = "行政区级")
    @Size(max = 10, message = "行政区级长度不能超过10")
    @TableField(value = "level", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.AREA_LEVEL)
    @Excel(name = "行政区级")
    private String level;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    @Size(max = 255, message = "数据来源长度不能超过255")
    @TableField(value = "source_", condition = LIKE)
    @Excel(name = "数据来源")
    private String source;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    @Excel(name = "状态", replace = {"是_true", "否_false", "_null"})
    private Boolean state;


    @Builder
    public Area(Long id, String label, Integer sortValue, Long parentId, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
                String code, String fullName, String longitude, String latitude, String level,
                String source, Boolean state) {
        this.id = id;
        this.label = label;
        this.sortValue = sortValue;
        this.parentId = parentId;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.code = code;
        this.fullName = fullName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.level = level;
        this.source = source;
        this.state = state;
    }

}
