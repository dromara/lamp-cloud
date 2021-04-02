package com.tangyh.lamp.example.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.annotation.echo.Echo;
import com.tangyh.basic.base.entity.Entity;
import com.tangyh.basic.model.RemoteData;
import com.tangyh.lamp.common.constant.DictionaryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.tangyh.lamp.common.constant.EchoConstants.DICTIONARY_ITEM_FEIGN_CLASS;
import static com.tangyh.lamp.common.constant.EchoConstants.FIND_NAME_BY_IDS;
import static com.tangyh.lamp.common.constant.EchoConstants.ORG_ID_FEIGN_CLASS;

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
@Accessors(chain = true)
@TableName("b_order")
@ApiModel(value = "Order", description = "订单(用于测试)")
public class Order extends Entity<Long> {
//public class Order {
//    @TableId(value = "id", type = IdType.INPUT)
//    protected Long id;
//
//    @TableField(value = "create_time", fill = FieldFill.INSERT)
//    protected LocalDateTime createTime;
//
//    @TableField(value = "create_user", fill = FieldFill.INSERT)
//    protected Long createUser;
//    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
//    protected LocalDateTime updateTime;
//
//    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
//    protected Long updateUser;

    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Size(max = 255, message = "名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "名称")
    private String name;

    /**
     * 学历
     *
     * @Echo(api = "orderServiceImpl", method = FIND_NAME_BY_IDS, dictType = DictionaryType.EDUCATION) RemoteData<String, String>
     */
    @ApiModelProperty(value = "学历")
    @Size(max = 255, message = "学历长度不能超过255")
    @TableField(value = "education", condition = LIKE)
    @Echo(api = "orderServiceImpl", method = FIND_NAME_BY_IDS, dictType = DictionaryType.EDUCATION)
    @ExcelEntity(name = "")
    @Excel(name = "学历")
    private RemoteData<String, String> education;

    /**
     * 民族
     *
     * @Echo(api = DICTIONARY_ITEM_FEIGN_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.NATION) RemoteData<String, String>
     */
    @ApiModelProperty(value = "民族")
    @Size(max = 255, message = "民族长度不能超过255")
    @TableField(value = "nation", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_FEIGN_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.NATION)
    @ExcelEntity(name = "")
    @Excel(name = "民族")
    private RemoteData<String, String> nation;

    /**
     * 组织ID
     * #c_org
     *
     * @Echo(api = ORG_ID_FEIGN_CLASS, method = FIND_NAME_BY_IDS) RemoteData<Long, String>
     */
    @ApiModelProperty(value = "组织ID")
    @TableField("org_id")
    @Echo(api = ORG_ID_FEIGN_CLASS, method = FIND_NAME_BY_IDS)
    @ExcelEntity(name = "")
    @Excel(name = "组织ID")
    private RemoteData<Long, String> org;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @Size(max = 255, min = 0, message = "编号长度不能超过255")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "编号")
    private String code;


    @Builder
    public Order(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                 String name, RemoteData<String, String> education, RemoteData<String, String> nation, RemoteData<Long, String> orgId, String code) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createUser;
        this.updateTime = updateTime;
        this.updatedBy = updateUser;
        this.name = name;
        this.education = education;
        this.nation = nation;
        this.org = orgId;
        this.code = code;
    }

}
