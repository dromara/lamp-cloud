package com.tangyh.lamp.authority.entity.common;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 系统日志扩展
 * </p>
 *
 * @author zuihou
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_opt_log_ext")
@ApiModel(value = "OptLogExt", description = "系统日志扩展")
@AllArgsConstructor
public class OptLogExt extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")

    @TableField("params")
    @Excel(name = "请求参数")
    private String params;

    /**
     * 返回值
     */
    @ApiModelProperty(value = "返回值")

    @TableField("result")
    @Excel(name = "返回值")
    private String result;

    /**
     * 异常描述
     */
    @ApiModelProperty(value = "异常描述")

    @TableField("ex_detail")
    @Excel(name = "异常描述")
    private String exDetail;


    @Builder
    public OptLogExt(Long id, LocalDateTime createTime, Long createdBy,
                     String params, String result, String exDetail) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.params = params;
        this.result = result;
        this.exDetail = exDetail;
    }

}
