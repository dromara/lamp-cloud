package com.github.zuihou.order.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.common.constant.DictionaryType;
import com.github.zuihou.injection.annonation.InjectionField;
import com.github.zuihou.model.RemoteData;
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

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.github.zuihou.common.constant.InjectionFieldConstants.DICTIONARY_ITEM_FEIGN_CLASS;
import static com.github.zuihou.common.constant.InjectionFieldConstants.DICTIONARY_ITEM_METHOD;
import static com.github.zuihou.common.constant.InjectionFieldConstants.ORG_ID_FEIGN_CLASS;
import static com.github.zuihou.common.constant.InjectionFieldConstants.ORG_ID_NAME_METHOD;

/**
 * <p>
 * 实体类
 * 订单(用于测试)
 * </p>
 *
 * @author zuihou
 * @since 2020-06-19
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("m_order")
@ApiModel(value = "Order", description = "订单(用于测试)")
@AllArgsConstructor
public class Order extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Length(max = 255, message = "名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "名称")
    private String name;

    /**
     * 学历
     *
     * @InjectionField(api = "orderServiceImpl", method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.EDUCATION) RemoteData<String, String>
     */
    @ApiModelProperty(value = "学历")
    @Length(max = 255, message = "学历长度不能超过255")
    @TableField(value = "education", condition = LIKE)
    @InjectionField(api = "orderServiceImpl", method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.EDUCATION)
    @ExcelEntity(name = "")
    @Excel(name = "学历")
    private RemoteData<String, String> education;

    /**
     * 民族
     *
     * @InjectionField(api = DICTIONARY_ITEM_FEIGN_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.NATION) RemoteData<String, String>
     */
    @ApiModelProperty(value = "民族")
    @Length(max = 255, message = "民族长度不能超过255")
    @TableField(value = "nation", condition = LIKE)
    @InjectionField(api = DICTIONARY_ITEM_FEIGN_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.NATION)
    @ExcelEntity(name = "")
    @Excel(name = "民族")
    private RemoteData<String, String> nation;

    /**
     * 组织ID
     * #c_core_org
     * @InjectionField(api = ORG_ID_FEIGN_CLASS, method = ORG_ID_NAME_METHOD) RemoteData<Long, String>
     */
    @ApiModelProperty(value = "组织ID")
    @TableField("org_id")
    @InjectionField(api = ORG_ID_FEIGN_CLASS, method = ORG_ID_NAME_METHOD)
    @ExcelEntity(name = "")
    @Excel(name = "组织ID")
    private RemoteData<Long, String> org;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @Length(max = 255, min = 0, message = "编号长度不能超过255")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "编号")
    private String code;


    @Builder
    public Order(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                 String name, RemoteData<String, String> education, RemoteData<String, String> nation, RemoteData<Long, String> orgId, String code) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.name = name;
        this.education = education;
        this.nation = nation;
        this.org = orgId;
        this.code = code;
    }

}
