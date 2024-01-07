package top.tangyh.lamp.oauth.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.base.entity.user.BaseOrg;

import java.util.List;

/**
 * 登录参数
 *
 * @author zuihou
 * @date 2020年01月05日22:18:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "部门信息结果")
public class OrgResultVO {

    @Schema(description = "当前租户下，所属单位")
    private List<BaseOrg> companyList;
    @Schema(description = "当前租户下和单位下，所属部门")
    private List<BaseOrg> deptList;
    /**
     * 当前单位ID
     */
    @Schema(description = "当前单位ID")
    private Long currentCompanyId;
    /**
     * 当前部门ID
     */
    @Schema(description = "当前部门ID")
    private Long currentDeptId;

}
