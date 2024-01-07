package top.tangyh.lamp.model.entity.base;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.basic.interfaces.echo.EchoVO;
import top.tangyh.lamp.model.constant.EchoApi;
import top.tangyh.lamp.model.constant.EchoDictType;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * 角色
 *
 * @author zuihou
 * @date 2019/07/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=false)
@Schema(description = "角色")
@TableName("base_role")
@Builder
public class SysRole extends Entity<Long> implements Serializable, EchoVO {
    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();


    /**
     * 角色类型;10-系统角色 20-自定义角色
     */
    @Schema(description = "角色类型")
    @TableField(value = "type_", condition = LIKE)
    private String type;
    /**
     * 角色类别;[10-功能角色 20-桌面角色 30-数据角色]
     */
    @Schema(description = "角色类别")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.ROLE_CATEGORY)
    @TableField(value = "category", condition = LIKE)
    private String category;
    /**
     * 名称
     */
    @Schema(description = "名称")
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 编码
     */
    @Schema(description = "编码")
    @TableField(value = "code", condition = LIKE)
    private String code;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;
    /**
     * 状态
     */
    @Schema(description = "状态")
    @TableField(value = "state")
    private Boolean state;
    /**
     * 内置角色
     */
    @Schema(description = "内置角色")
    @TableField(value = "readonly_")
    private Boolean readonly;

    /**
     * 创建者组织ID
     */
    @Schema(description = "创建者组织ID")
    @TableField(value = "created_org_id")
    private Long createdOrgId;

    /**
     * 角色列表转换成角色编码列表
     */
    public static List<String> getRoleCode(List<SysRole> list) {
        if (ArrayUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(SysRole::getCode).toList();
    }

    /**
     * 指定角色编码是否在角色列表中
     */
    public static boolean contains(List<SysRole> list, String code) {
        if (ArrayUtil.isEmpty(list) || StrUtil.isEmpty(code)) {
            return false;
        }
        return list.stream().anyMatch((item) -> code.equals(item.getCode()));
    }
}
