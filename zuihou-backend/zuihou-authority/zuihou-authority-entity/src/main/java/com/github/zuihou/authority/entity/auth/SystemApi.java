package com.github.zuihou.authority.entity.auth;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;

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

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * API接口
 * </p>
 *
 * @author zuihou
 * @since 2019-12-17
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
     * 接口编码
     */
    @ApiModelProperty(value = "接口编码")
    @NotEmpty(message = "接口编码不能为空")
    @Length(max = 255, message = "接口编码长度不能超过255")
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @NotEmpty(message = "接口名称不能为空")
    @Length(max = 100, message = "接口名称长度不能超过100")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 资源描述
     */
    @ApiModelProperty(value = "资源描述")
    @Length(max = 100, message = "资源描述长度不能超过100")
    @TableField(value = "describe_", condition = LIKE)
    private String describe;

    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式")
    @Length(max = 255, message = "请求方式长度不能超过255")
    @TableField(value = "request_method", condition = LIKE)
    private String requestMethod;

    /**
     * 响应类型
     */
    @ApiModelProperty(value = "响应类型")
    @Length(max = 255, message = "响应类型长度不能超过255")
    @TableField(value = "content_type", condition = LIKE)
    private String contentType;

    /**
     * 服务ID
     */
    @ApiModelProperty(value = "服务ID")
    @NotEmpty(message = "服务ID不能为空")
    @Length(max = 50, message = "服务ID长度不能超过50")
    @TableField(value = "service_id", condition = LIKE)
    private String serviceId;

    /**
     * 请求路径
     */
    @ApiModelProperty(value = "请求路径")
    @Length(max = 255, message = "请求路径长度不能超过255")
    @TableField(value = "path", condition = LIKE)
    private String path;

    /**
     * 状态
     * :0-无效 1-有效
     */
    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Boolean status;

    /**
     * 保留数据
     * 0-否 1-是 系统内资数据,不允许删除
     */
    @ApiModelProperty(value = "保留数据")
    @TableField("is_persist")
    private Boolean isPersist;

    /**
     * 是否需要认证
     * : 0-无认证 1-身份认证 默认:1
     */
    @ApiModelProperty(value = "是否需要认证")
    @TableField("is_auth")
    private Boolean isAuth;

    /**
     * 是否公开
     * : 0-内部的 1-公开的
     */
    @ApiModelProperty(value = "是否公开")
    @TableField("is_open")
    private Boolean isOpen;

    /**
     * 类名
     */
    @ApiModelProperty(value = "类名")
    @Length(max = 255, message = "类名长度不能超过255")
    @TableField(value = "class_name", condition = LIKE)
    private String className;

    /**
     * 方法名
     */
    @ApiModelProperty(value = "方法名")
    @Length(max = 255, message = "方法名长度不能超过255")
    @TableField(value = "method_name", condition = LIKE)
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
