package top.tangyh.lamp.system.vo.result.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.system.entity.application.DefApplication;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author tangyh
 * @version 4.0
 * @date 2021/9/27 11:37 下午
 * @create [2021/9/27 11:37 下午 ] [tangyh] [初始创建]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "应用资源返回")
public class ApplicationResourceResultVO implements Serializable {
    private DefApplication defApplication;
    private Collection<DefResourceResultVO> resourceList;
}
