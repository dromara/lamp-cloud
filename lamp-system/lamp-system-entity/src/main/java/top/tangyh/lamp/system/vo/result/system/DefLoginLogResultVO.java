package top.tangyh.lamp.system.vo.result.system;


import cn.hutool.core.map.MapUtil;
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
import java.util.Map;


/**
 * <p>
 * 实体类
 * 登录日志
 * </p>
 *
 * @author zuihou
 * @since 2021-11-12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "登录日志")
public class DefLoginLogResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "主键")
    private Long id;

    /**
     * 登录员工
     */
    @Schema(description = "登录员工")
    
    private Long employeeId;
    /**
     * 登录用户
     */
    @Schema(description = "登录用户")
    
    private Long userId;
    /**
     * 登录IP
     */
    @Schema(description = "登录IP")
    
    private String requestIp;
    /**
     * 登录人姓名
     */
    @Schema(description = "登录人姓名")
    
    private String nickName;
    /**
     * 登录人账号
     */
    @Schema(description = "登录人账号")
    
    private String username;
    /**
     * 登录描述
     */
    @Schema(description = "登录描述")
    
    private String description;
    /**
     * 登录时间
     */
    @Schema(description = "登录时间")
    
    private String loginDate;
    /**
     * 浏览器请求头
     */
    @Schema(description = "浏览器请求头")
    
    private String ua;
    /**
     * 浏览器名称
     */
    @Schema(description = "浏览器名称")
    
    private String browser;
    /**
     * 浏览器版本
     */
    @Schema(description = "浏览器版本")
    
    private String browserVersion;
    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    
    private String operatingSystem;
    /**
     * 登录地点
     */
    @Schema(description = "登录地点")
    
    private String location;

    /**
     * '登录状态;[01-登录成功 02-验证码错误 03-密码错误 04-账号锁定 05-切换租户 06-短信验证码错误]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.LOGIN_STATUS)
     */
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.LOGIN_STATUS)
    @Schema(description = "登录状态")
    private String status;
}
