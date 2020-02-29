package com.github.zuihou.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文件修改
 *
 * @author zuihou
 * @date 2019-05-06
 */
@Data
@ApiModel(value = "FileUpdate", description = "文件修改")
public class FileUpdateDTO implements Serializable {
    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "文件id不能为空")
    private Long id;
    /**
     * 原始文件名
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "文件原始名称")
    private String submittedFileName;

}
