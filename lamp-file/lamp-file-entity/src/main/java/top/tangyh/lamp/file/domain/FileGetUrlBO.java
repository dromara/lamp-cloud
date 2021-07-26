package top.tangyh.lamp.file.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 附件
 * </p>
 *
 * @author zuihou
 * @since 2021-06-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "FileGetUrlVO", description = "附件查询")
public class FileGetUrlBO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "请传入文件路径")
    private String path;
    private String originalFileName;

    private String bucket;
}
