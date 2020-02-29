package com.github.zuihou.demo.controller.test.model;

import com.github.zuihou.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

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
public class ValidatorDTO extends Entity<Long> {

    /**
     * 应用编码
     * 必须唯一
     */
    @ApiModelProperty(value = "应用编码")
    @NotEmpty(message = "应用编码不能为空")
    @Length(max = 20, message = "应用编码长度不能超过20")
    private String code;

    @NotNull(message = "age不能为空")
    @Range(max = 20, message = "age长度不能超过20")
    private Long age;

    @NotBlank(message = "名称不能为空")
    private String name;

    @Null
    private String nulls;

    @NotNull()
    private Long notnull;

    @Email(message = "email 不符合")
    private String email;

    @URL(message = "url 不符合规则")
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

    @Valid
    private InnerDTO innerDTO;

    @Valid
    private List<InnerDTO> innerDTOs;

}
