package com.tangyh.lamp.authority.dto.auth;

import com.tangyh.lamp.authority.enumeration.auth.Sex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author zuihou
 * @since 2021-04-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserPageQuery", description = "用户")
public class UserPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String account;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 组织
     * #c_org
     *
     * @Echo(api = ORG_ID_CLASS, method = FIND_BY_IDS, beanClass = Org.class)
     */
    @ApiModelProperty(value = "组织")
    private Long orgId;
    /**
     * 岗位
     * #c_station
     *
     * @Echo(api = STATION_ID_CLASS, method = FIND_NAME_BY_IDS)
     */
    @ApiModelProperty(value = "岗位")
    private Long stationId;
    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    private Boolean readonly;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobile;
    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    @ApiModelProperty(value = "性别")
    private List<Sex> sex;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * 民族
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.NATION)
     */
    @ApiModelProperty(value = "民族")
    private List<String> nation;
    /**
     * 学历
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.EDUCATION)
     */
    @ApiModelProperty(value = "学历")
    private List<String> education;
    /**
     * 职位状态
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.POSITION_STATUS)
     */
    @ApiModelProperty(value = "职位状态")
    private List<String> positionStatus;
    /**
     * 工作描述
     */
    @ApiModelProperty(value = "工作描述")
    private String workDescribe;
    /**
     * 最后一次输错密码时间
     */
    @ApiModelProperty(value = "最后一次输错密码时间")
    private LocalDateTime passwordErrorLastTime;
    /**
     * 密码错误次数
     */
    @ApiModelProperty(value = "密码错误次数")
    private Integer passwordErrorNum;
    /**
     * 密码过期时间
     */
    @ApiModelProperty(value = "密码过期时间")
    private LocalDateTime passwordExpireTime;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 密码盐
     */
    @ApiModelProperty(value = "密码盐")
    private String salt;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

}
