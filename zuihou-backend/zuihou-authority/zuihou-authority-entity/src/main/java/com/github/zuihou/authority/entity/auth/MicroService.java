//package com.github.zuihou.authority.entity.auth;
//
//import java.time.LocalDateTime;
//
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.github.zuihou.base.entity.Entity;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import lombok.experimental.Accessors;
//import org.hibernate.validator.constraints.Length;
//
//import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
//
///**
// * <p>
// * 实体类
// * 服务表
// * </p>
// *
// * @author zuihou
// * @since 2019-10-20
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
//@Accessors(chain = true)
//@TableName("c_auth_micro_service")
//@ApiModel(value = "MicroService", description = "服务表")
//public class MicroService extends Entity<Long> {
//
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 服务名称
//     */
//    @ApiModelProperty(value = "服务名称")
//    @Length(max = 20, message = "服务名称长度不能超过20")
//    @TableField(value = "name", condition = LIKE)
//    private String name;
//
//    /**
//     * 服务描述
//     */
//    @ApiModelProperty(value = "服务描述")
//    @Length(max = 100, message = "服务描述长度不能超过100")
//    @TableField(value = "describe_", condition = LIKE)
//    private String describe;
//
//    /**
//     * eureka编码
//     * 就是服务注册到eureka后，他的application name
//     */
//    @ApiModelProperty(value = "eureka编码")
//    @Length(max = 100, message = "eureka编码长度不能超过100")
//    @TableField(value = "eureka_code", condition = LIKE)
//    private String eurekaCode;
//
//    /**
//     * swagger地址
//     */
//    @ApiModelProperty(value = "swagger地址")
//    @Length(max = 255, message = "swagger地址长度不能超过255")
//    @TableField(value = "swagger_url", condition = LIKE)
//    private String swaggerUrl;
//
//    /**
//     * 排序
//     */
//    @ApiModelProperty(value = "排序")
//    @TableField("sort_value")
//    private Integer sortValue;
//
//
//    @Builder
//    public MicroService(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
//                        String name, String describe, String eurekaCode, String swaggerUrl, Integer sortValue) {
//        this.id = id;
//        this.createUser = createUser;
//        this.createTime = createTime;
//        this.updateUser = updateUser;
//        this.updateTime = updateTime;
//        this.name = name;
//        this.describe = describe;
//        this.eurekaCode = eurekaCode;
//        this.swaggerUrl = swaggerUrl;
//        this.sortValue = sortValue;
//    }
//
//}
