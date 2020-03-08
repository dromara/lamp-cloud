package com.github.zuihou.authority.dto.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import com.github.zuihou.authority.enumeration.auth.Sex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.github.zuihou.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/09/11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserExcelVO", description = "用户")
public class UserExcelVO implements Serializable, IExcelModel, IExcelDataModel {

    private String errorMsg;
    private Integer rowNum;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Length(max = 30, message = "账号长度不能超过30")
    @Excel(name = "账号")
    private String account;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 50, message = "姓名长度不能超过50")
    @Excel(name = "姓名")
    private String name;
    /**
     * 组织ID
     * #c_core_org
     *
     * @InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.github.zuihou.authority.entity.core.Org>
     */
    @ApiModelProperty(value = "组织ID")
    @Excel(name = "组织")
    private Long org;
    /**
     * 岗位ID
     * #c_core_station
     *
     * @InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD) RemoteData<Long, String>
     */
    @ApiModelProperty(value = "岗位ID")
    @Excel(name = "岗位")
    private Long station;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Length(max = 255, message = "邮箱长度不能超过255")
    @Excel(name = "邮箱")
    private String email;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Length(max = 20, message = "手机长度不能超过20")
    @Excel(name = "手机")
    private String mobile;
    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    @ApiModelProperty(value = "性别")
    @Excel(name = "性别", replace = {"男_M", "女_W", "未知_N", "_null"}, enumImportMethod = "get")
    private Sex sex;
    /**
     * 状态
     * 1启用 0禁用
     */
    @ApiModelProperty(value = "状态")
    @Excel(name = "状态", replace = {"启用_true", "禁用_false", "_null"})
    private Boolean status;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @Length(max = 255, message = "头像长度不能超过255")
    @Excel(name = "头像")
    private String avatar;
    /**
     * 民族
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "民族")
    @Length(max = 20, message = "民族长度不能超过20")
    @Excel(name = "民族", dict = "NATION")
    private String nation;
    /**
     * 学历
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "学历")
    @Length(max = 20, message = "学历长度不能超过20")
    @Excel(name = "学历", dict = "EDUCATION")
    private String education;
    /**
     * 职位状态
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "职位状态")
    @Length(max = 20, message = "职位状态长度不能超过20")
    @Excel(name = "职位状态", dict = "POSITION_STATUS")
    private String positionStatus;
    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
     */
    @ApiModelProperty(value = "工作描述")
    @Length(max = 255, message = "工作描述长度不能超过255")
    @Excel(name = "工作描述")
    private String workDescribe;
    /**
     * 密码过期时间
     */
    @ApiModelProperty(value = "密码过期时间")
    @Excel(name = "密码过期时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime passwordExpireTime;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    @Excel(name = "最后登录时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime lastLoginTime;

    /**
     * 获取行号
     *
     * @return
     */
    @Override
    public Integer getRowNum() {
        return rowNum;
    }

    /**
     * 设置行号
     *
     * @param rowNum
     */
    @Override
    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * 获取错误数据
     *
     * @return
     */
    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 设置错误信息
     *
     * @param errorMsg
     */
    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


}
