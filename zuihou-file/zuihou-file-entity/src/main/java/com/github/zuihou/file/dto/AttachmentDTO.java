package com.github.zuihou.file.dto;

import com.github.zuihou.file.entity.Attachment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 附件表
 * </p>
 *
 * @author zuihou
 * @since 2019-05-20
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AttachmentDTO", description = "附件表")
public class AttachmentDTO extends Attachment implements Serializable {

    /**
     * 在DTO中新增并自定义字段，需要覆盖验证的字段，请新建DTO。Entity中的验证规则可以自行修改，但下次生成代码时，记得同步代码！！
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "文件下载地址 根据url下载")
    private String downloadUrlByUrl;
    @ApiModelProperty(value = "文件下载地址 根据文件id下载")
    private String downloadUrlById;
    @ApiModelProperty(value = "文件下载地址 根据业务id下载")
    private String downloadUrlByBizId;

    public static AttachmentDTO build() {
        return new AttachmentDTO();
    }
}
