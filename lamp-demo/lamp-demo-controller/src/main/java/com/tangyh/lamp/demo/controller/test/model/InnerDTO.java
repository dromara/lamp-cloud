package com.tangyh.lamp.demo.controller.test.model;

import com.tangyh.basic.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 表单验证测试类
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
@ApiModel(value = "InnerDTO", description = "InnerDTO验证对象")
public class InnerDTO extends Entity<Long> {

    /**
     * 应用编码
     * 必须唯一
     */
    @ApiModelProperty(value = "应用编码")
    @NotEmpty(message = "应用编码不能为空")
    @Size(max = 20, message = "应用编码长度不能超过20")
    private String code;

    @NotNull(message = "age不能为空")
    private Long age;

    @NotBlank(message = "名称不能为空")
    private String name;

    @Null
    private String nulls;

    @NotNull()
    private Long notnull;

    @Email(message = "email 不符合")
    private String email;

    private String url;


    @Pattern(regexp = ".*", message = "url2 不符合规则")
    private String url2;

    @AssertTrue
    private Boolean flag1;
    @AssertFalse
    private Boolean flag2;


    @Min(3)
    @Max(5)
    private Long count1;

    @DecimalMin("3.5")
    @DecimalMax("5")
    private Double count2;

    @Size(max = 4)
    private List<String> list;


    @Past
    private LocalDateTime sendTime;

}
