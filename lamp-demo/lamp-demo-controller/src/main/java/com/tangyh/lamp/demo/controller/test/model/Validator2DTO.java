package com.tangyh.lamp.demo.controller.test.model;

import com.tangyh.basic.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * 表单验证 测试实体
 *
 * @author zuihou
 * @date 2019/07/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "HiberDTO", description = "测试验证对象")
public class Validator2DTO extends Entity<Long> {


    @Size(min = 2, max = 5, message = "name3 在2-5之间")
    private String name3;

    @Min(value = 3, message = "大于3")
    @Max(value = 5, message = "超过5")
    private String count444;

    @Min(3)
    @Max(5)
    private Long count1;

    @DecimalMin("3.5")
    @DecimalMax("5")
    private Double count2;

}
