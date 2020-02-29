package com.github.zuihou.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件夹
 *
 * @author zuihou
 * @date 2019-04-29
 */
@Data
@ApiModel(value = "Folder", description = "文件夹")
public class FolderDTO extends BaseFolderDTO implements Serializable {
    @ApiModelProperty(value = "id", notes = "文件夹id", required = true)
    private Long id;
}
