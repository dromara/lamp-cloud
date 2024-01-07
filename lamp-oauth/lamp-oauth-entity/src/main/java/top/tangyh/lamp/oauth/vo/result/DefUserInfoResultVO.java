package top.tangyh.lamp.oauth.vo.result;


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
import top.tangyh.lamp.base.vo.result.user.BaseEmployeeResultVO;
import top.tangyh.lamp.model.constant.EchoApi;
import top.tangyh.lamp.model.constant.EchoDictType;
import top.tangyh.lamp.system.vo.result.application.DefApplicationResultVO;

import java.io.Serializable;
import java.util.Map;


/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author zuihou
 * @since 2021-10-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "用户")
public class DefUserInfoResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "主键")
    private Long id;

    /**
     * 用户名;大小写数字下划线
     */
    @Schema(description = "用户名")
    
    private String username;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    
    private String nickName;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    
    private String email;
    /**
     * 手机;1开头11位纯数字
     */
    @Schema(description = "手机")
    
    private String mobile;
    /**
     * 身份证;15或18位
     */
    @Schema(description = "身份证")
    
    private String idCard;
    /**
     * 微信OpenId
     */
    @Schema(description = "微信OpenId")
    
    private String wxOpenId;
    /**
     * 钉钉OpenId
     */
    @Schema(description = "钉钉OpenId")
    
    private String ddOpenId;
    /**
     * 内置;[0-否 1-是]
     */
    @Schema(description = "内置")
    
    private Boolean readonly;
    /**
     * 性别;
     * #Sex{W:女;M:男;N:未知}
     */
    @Schema(description = "性别")
    
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.SEX)
    private String sex;
    /**
     * 民族;[01-汉族 99-其他]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.NATION)
     */
    @Schema(description = "民族")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.NATION)
    
    private String nation;
    /**
     * 学历;[01-小学 02-中学 03-高中 04-专科 05-本科 06-硕士 07-博士 08-博士后 99-其他]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.EDUCATION)
     */
    @Schema(description = "学历")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.EDUCATION)
    
    private String education;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    
    private Boolean state;

    @Schema(description = "头像id")
    private Long avatarId;

    /**
     * 工作描述
     */
    @Schema(description = "工作描述")
    
    private String workDescribe;

    @Schema(description = "员工ID")
    private Long employeeId;

    @Schema(description = "当前员工信息")
    private BaseEmployeeResultVO baseEmployee;

    @Schema(description = "当前应用信息")
    private DefApplicationResultVO defApplication;

    /** 为空时，默认页面由前端控制 */
    @Schema(description = "登录成功后，跳转的页面")
    private String homePath;
}
