package com.github.zuihou.file.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/05/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharePageDTO implements Serializable {
    @ApiModelProperty(value = "分享id")
    @NotNull(message = "分享id不能为空")
    private Long id;
    @ApiModelProperty(value = "子文件名称")
    private String name;
    @ApiModelProperty(value = "密码")
    private String password;
}
