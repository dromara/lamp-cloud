//package com.github.zuihou.base.entity;
//
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.TableField;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import lombok.experimental.Accessors;
//
//import java.time.LocalDateTime;
//
///**
// * 基础实体
// *
// * @author zuihou
// * @date 2019/05/05
// */
//@Getter
//@Setter
//@Accessors(chain = true)
//@ToString(callSuper = true)
//public class Entity<T> extends SuperEntity<T> {
//
//    public static final String UPDATE_TIME = "updateTime";
//    public static final String UPDATE_USER = "updateUser";
//    private static final long serialVersionUID = 5169873634279173683L;
//
//    @ApiModelProperty(value = "最后修改时间")
//    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
//    protected LocalDateTime updateTime;
//
//    @ApiModelProperty(value = "最后修改人ID")
//    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
//    protected T updateUser;
//
//    public Entity(T id, LocalDateTime createTime, T createUser, LocalDateTime updateTime, T updateUser) {
//        super(id, createTime, createUser);
//        this.updateTime = updateTime;
//        this.updateUser = updateUser;
//    }
//
//    public Entity() {
//    }
//
//}
