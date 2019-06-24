package com.github.zuihou.file.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.zuihou.common.enums.DateType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/05/08
 */
@Data
@ApiModel(value = "SaveShare", description = "分享保存")
public class ShareSaveDTO {
    /**
     * 需要分享的文件id集合
     */
    @ApiModelProperty(value = "需要分享的文件id集合")
    @NotNull(message = "分享文件id集合不能为空")
    @Size(min = 1, max = 100, message = "同时分享的文件id个数只能介于1~100之间")
    private List<Long> ids;
    /**
     * 文件是否加密
     */
    @ApiModelProperty(value = "文件是否加密")
    @NotNull(message = "文件是否加密不能为空")
    private Boolean isPwd;
    /**
     * 是否存在有效期-  NUL:无限期; DAY:1天; WEEK:7天
     */
    @ApiModelProperty(value = "是否存在有效期")
    private DateType dateType;
}
