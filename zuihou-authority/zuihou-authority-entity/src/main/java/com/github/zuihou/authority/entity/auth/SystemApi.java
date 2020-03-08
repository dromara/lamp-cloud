package com.github.zuihou.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * API接口
 * </p>
 *
 * @author zuihou
 * @since 2019-12-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_system_api")
@ApiModel(value = "SystemApi", description = "API接口")
public class SystemApi extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @Length(max = 255, message = "编码长度不能超过255")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "编码", width = 20)
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 100, message = "名称长度不能超过100")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "名称", width = 20)
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Length(max = 100, message = "描述长度不能超过100")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "描述", width = 40)
    private String describe;

    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式")
    @Length(max = 255, message = "请求方式长度不能超过255")
    @TableField(value = "request_method", condition = LIKE)
    @Excel(name = "请求方式")
    private String requestMethod;

    /**
     * 响应类型
     */
    @ApiModelProperty(value = "响应类型")
    @Length(max = 255, message = "响应类型长度不能超过255")
    @TableField(value = "content_type", condition = LIKE)
    @Excel(name = "响应类型", width = 20)
    private String contentType;

    /**
     * 服务ID
     */
    @ApiModelProperty(value = "服务ID")
    @NotEmpty(message = "服务ID不能为空")
    @Length(max = 50, message = "服务ID长度不能超过50")
    @TableField(value = "service_id", condition = LIKE)
    @Excel(name = "服务ID", width = 20)
    private String serviceId;

    /**
     * 请求路径
     */
    @ApiModelProperty(value = "请求路径")
    @Length(max = 255, message = "请求路径长度不能超过255")
    @TableField(value = "path", condition = LIKE)
    @Excel(name = "请求路径", width = 40)
    private String path;

    /**
     * 状态
     * :0-无效 1-有效
     */
    @ApiModelProperty(value = "状态")
    @TableField("status")
    @Excel(name = "状态", replace = {"启用_true", "禁用_false", "_null"})
    private Boolean status;

    /**
     * 保留数据
     * 0-否 1-是 系统内资数据,不允许删除
     */
    @ApiModelProperty(value = "保留数据")
    @TableField("is_persist")
    @Excel(name = "保留数据", replace = {"是_true", "否_false", "_null"})
    private Boolean isPersist;

    /**
     * 是否需要认证
     * : 0-无认证 1-身份认证 默认:1
     */
    @ApiModelProperty(value = "是否需要认证")
    @TableField("is_auth")
    @Excel(name = "是否需要认证", replace = {"是_true", "否_false", "_null"})
    private Boolean isAuth;

    /**
     * 是否公开
     * : 0-内部的 1-公开的
     */
    @ApiModelProperty(value = "是否公开")
    @TableField("is_open")
    @Excel(name = "是否公开", replace = {"是_true", "否_false", "_null"})
    private Boolean isOpen;

    /**
     * 类名
     */
    @ApiModelProperty(value = "类名")
    @Length(max = 255, message = "类名长度不能超过255")
    @TableField(value = "class_name", condition = LIKE)
    @Excel(name = "类名", width = 40)
    private String className;

    /**
     * 方法名
     */
    @ApiModelProperty(value = "方法名")
    @Length(max = 255, message = "方法名长度不能超过255")
    @TableField(value = "method_name", condition = LIKE)
    @Excel(name = "方法名", width = 40)
    private String methodName;


    @Builder
    public SystemApi(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                     String code, String name, String describe, String requestMethod, String contentType,
                     String serviceId, String path, Boolean status, Boolean isPersist, Boolean isAuth, Boolean isOpen,
                     String className, String methodName) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.code = code;
        this.name = name;
        this.describe = describe;
        this.requestMethod = requestMethod;
        this.contentType = contentType;
        this.serviceId = serviceId;
        this.path = path;
        this.status = status;
        this.isPersist = isPersist;
        this.isAuth = isAuth;
        this.isOpen = isOpen;
        this.className = className;
        this.methodName = methodName;
    }

}
