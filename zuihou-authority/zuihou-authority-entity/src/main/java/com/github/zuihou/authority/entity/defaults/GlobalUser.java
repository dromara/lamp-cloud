package com.github.zuihou.authority.entity.defaults;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.zuihou.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @since 2019-10-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("d_global_user")
@ApiModel(value = "GlobalUser", description = "全局账号")
public class GlobalUser extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotEmpty(message = "租户编号不能为空")
    @Length(max = 10, message = "租户编号长度不能超过10")
    @TableField(value = "tenant_code", condition = LIKE)
    @Excel(name = "租户编号")
    private String tenantCode;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Length(max = 30, message = "账号长度不能超过30")
    @TableField(value = "account", condition = LIKE)
    @Excel(name = "账号", width = 20)
    private String account;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Length(max = 20, message = "手机长度不能超过20")
    @TableField(value = "mobile", condition = LIKE)
    private String mobile;
    @ApiModelProperty(value = "是否只读")
    @TableField(value = "readonly")
    @Excel(name = "手机", width = 20)
    private Boolean readonly;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Length(max = 50, message = "姓名长度不能超过20")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "姓名", width = 20)
    private String name;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Length(max = 255, message = "邮箱长度不能超过255")
    @TableField(value = "email", condition = LIKE)
    @Excel(name = "邮箱", width = 20)
    private String email;

    @ApiModelProperty(value = "密码")
    @Length(max = 64, message = "密码长度不能超过64")
    @TableField(value = "password", condition = LIKE)
    @JsonIgnore
    private String password;

    @Builder
    public GlobalUser(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                      String tenantCode, String account, Boolean readonly, String mobile, String name, String email, String password) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.tenantCode = tenantCode;
        this.readonly = readonly;
        this.account = account;
        this.mobile = mobile;
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
