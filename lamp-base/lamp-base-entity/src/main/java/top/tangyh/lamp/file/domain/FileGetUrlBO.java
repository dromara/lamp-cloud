package top.tangyh.lamp.file.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@Schema(description = "附件查询")
public class FileGetUrlBO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "请传入文件路径")
    private String path;
    private String originalFileName;

    private String bucket;
}
