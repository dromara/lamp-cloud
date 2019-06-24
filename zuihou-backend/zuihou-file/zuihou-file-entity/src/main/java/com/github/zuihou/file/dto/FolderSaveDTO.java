package com.github.zuihou.file.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 文件夹保存
 *
 * @author tangyh
 * @date 2019-04-29
 */
@Data
@ApiModel(value = "FolderSave", description = "文件夹保存")
public class FolderSaveDTO extends BaseFolderDTO implements Serializable {
}
