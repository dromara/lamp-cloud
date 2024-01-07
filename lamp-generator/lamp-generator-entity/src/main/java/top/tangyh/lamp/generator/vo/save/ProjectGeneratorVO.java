package top.tangyh.lamp.generator.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.generator.enumeration.ProjectTypeEnum;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 代码生成
 * </p>
 *
 * @author zuihou
 * @since 2022-03-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "项目生成")
public class ProjectGeneratorVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 作者 */
    @NotEmpty(message = "请填写作者")
    public String author;
    /** cloud boot */
    @NotNull(message = "请填写类型")
    private ProjectTypeEnum type;
    /** 输出路径 */
    @NotEmpty(message = "请填写输出路径")

    private String outputDir;
    /** 项目前缀 */
    @NotEmpty(message = "请填写项目前缀")
    private String projectPrefix;
    /**
     * 服务名
     * <p>
     * 如： lamp-base-server 中的base
     * 如： lamp-system-server system
     */
    @NotEmpty(message = "请填写服务名")
    private String serviceName;

    /**
     * 模块名
     * <p>
     * 如： top.tangyh.lamp.base.dao.common 包中的 base
     * 如： top.tangyh.lamp.file.dao.xxx 包中的 file
     */
    @NotEmpty(message = "请填写模块名")
    private String moduleName;

    /**
     * lamp项目 生成代码位于 src/main/java 下的基础包
     * 如： top.tangyh.lamp.base.dao.common 包中的 top.tangyh.lamp
     * 如： top.tangyh.lamp.file.dao.xxx 包中的 top.tangyh.lamp
     */
    @NotEmpty(message = "请填写基础包")
    private String parent;

    @NotEmpty(message = "请填写服务groupId")
    private String groupId;
    /**
     * lamp-util 基础包
     */
    @NotEmpty(message = "请填写工具类基础包")
    private String utilParent;
    @NotEmpty(message = "请填写工具类groupId")
    private String utilGroupId;
    @NotEmpty(message = "请填写版本号")
    private String version;
    /**
     * 中文服务名
     *
     * @author tangyh
     * @date 2022/4/5 2:34 PM
     * @create [2022/4/5 2:34 PM ] [tangyh] [初始创建]
     * @update [2022/4/5 2:34 PM ] [tangyh] [变更描述]
     */
    @NotEmpty(message = "请填写服务中文名")
    private String description;
    @NotNull(message = "请填写端口号")
    private Integer serverPort;
}
