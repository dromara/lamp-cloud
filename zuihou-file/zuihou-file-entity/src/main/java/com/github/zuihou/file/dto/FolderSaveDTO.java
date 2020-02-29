package com.github.zuihou.file.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件夹保存
 *
 * @author zuihou
 * @date 2019-04-29
 */
@Data
@ApiModel(value = "FolderSave", description = "文件夹保存")
public class FolderSaveDTO extends BaseFolderDTO implements Serializable {
}
