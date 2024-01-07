package top.tangyh.lamp.oauth.vo.param;

import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.model.vo.result.Option;

import java.util.List;
import java.util.Objects;

/**
 * @author tangyh
 * @version v1.0
 * @date 2021/9/16 11:49 下午
 * @create [2021/9/16 11:49 下午 ] [tangyh] [初始创建]
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@Builder
@Schema(description = "字典、枚举查询参数")
public class CodeQueryVO {
    @Schema(description = "字典类型或枚举类型")
    private String type;
    @Schema(description = "需要排除的字典条目或枚举条目")
    private List<String> excludes;

    @Schema(description = "扩展条目")
    private Option extend;

    @Schema(description = "扩展条目放在第一位还是最后一位")
    private Boolean extendFirst;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CodeQueryVO that = (CodeQueryVO) o;
        return Objects.equals(type, that.type) && CollUtil.isEqualList(excludes, that.excludes) && Objects.equals(extend, that.extend) && Objects.equals(extendFirst, that.extendFirst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, excludes, extend, extendFirst);
    }

}
