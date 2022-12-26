package top.tangyh.lamp.file.vo.param;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import top.tangyh.lamp.file.enumeration.FileStorageType;

import java.io.Serializable;

/**
 * 附件上传
 *
 * @author zuihou
 * @date 2019-05-12 18:49
 */
@Data
@Schema(description = "附件上传")
public class FileUploadVO implements Serializable {

    @Schema(description = "业务类型")
    @NotBlank(message = "请填写业务类型")
    private String bizType;

    @Schema(description = "桶")
    private String bucket;

    @Schema(description = "存储类型")
    private FileStorageType storageType;
}
