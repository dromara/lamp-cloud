package top.tangyh.lamp.model.vo.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源 查询DTO
 *
 * @author zuihou
 * @date 2019/06/05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "资源查询")
public class ResourceQueryDTO {

    /**
     * 父资源id， 用于查询按钮
     */
    @Schema(title = "菜单id", description = "指定菜单id")
    private Long menuId;
    /**
     * 登录人用户id
     */
    @Schema(title = "指定用户id", description = "指定用户id，前端不传则自动获取")
    private Long userId;

}
