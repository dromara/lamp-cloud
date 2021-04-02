package com.tangyh.lamp.example.dto;

import com.tangyh.lamp.common.enums.DateType;
import com.tangyh.lamp.example.enums.SexTest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * <p>
 * 实体类
 * 订单
 * </p>
 *
 * @author zuihou
 * @since 2019-08-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "RestTestDTO", description = "RestTestDTO")
public class RestTestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Size(max = 255, message = "名称长度不能超过255")
    private String name;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @Size(max = 255, message = "编号长度不能超过255")
    private String code;

    private Long id;
    private BigDecimal bd;
    private LocalDateTime ldt;
    private LocalDate ld;
    private LocalTime lt;
    private Date date;
    private DateType dateType;
    private SexTest sexTest;
}
