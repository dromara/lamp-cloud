package top.tangyh.lamp.authority.dto.auth;

import top.tangyh.lamp.model.enumeration.Sex;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/09/11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description="用户")
public class UserDTO implements Serializable {

    @Schema(description="ID")
    private Long id;

    /**
     * 账号
     */
    @Schema(description="账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 30, message = "账号长度不能超过30")
    private String account;

    /**
     * 姓名
     */
    @Schema(description="姓名")
    @NotEmpty(message = "姓名不能为空")
    @Size(max = 50, message = "姓名长度不能超过50")
    private String name;

    /**
     * 组织ID
     * #c_org
     */
    @Schema(description="组织ID")
    private Long org;

    /**
     * 岗位ID
     * #c_station
     */
    @Schema(description="岗位ID")
    private Long station;

    /**
     * 邮箱
     */
    @Schema(description="邮箱")
    @Size(max = 255, message = "邮箱长度不能超过255")
    private String email;

    /**
     * 手机
     */
    @Schema(description="手机")
    @Size(max = 20, message = "手机长度不能超过20")
    private String mobile;

    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    @Schema(description="性别")
    private Sex sex;

    /**
     * 启用状态 1启用 0禁用
     */
    @Schema(description="启用状态 1启用 0禁用")
    private Boolean status;

    /**
     * 照片
     */
    @Schema(description="头像")
    private String avatar;

    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
     */
    @Schema(description="工作描述")
    private String workDescribe;

    /**
     * 最后登录时间
     */
    @Schema(description="最后登录时间")
    private LocalDateTime lastLoginTime;

}
