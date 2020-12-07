package com.tangyh.lamp.authority.dto.common;

import com.tangyh.lamp.authority.entity.common.OptLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OptLogResult", description = "系统日志扩展")
public class OptLogResult extends OptLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String params;
    /**
     * 返回值
     */
    @ApiModelProperty(value = "返回值")
    private String result;
    /**
     * 异常描述
     */
    @ApiModelProperty(value = "异常描述")
    private String exDetail;

}
