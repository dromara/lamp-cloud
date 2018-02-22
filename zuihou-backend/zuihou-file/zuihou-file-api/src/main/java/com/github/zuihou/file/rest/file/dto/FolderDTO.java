package com.github.zuihou.file.rest.file.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FolderDTO extends BaseFolderDTO implements Serializable {
    @ApiModelProperty(value = "id", required = true)
    private Long id;
}
