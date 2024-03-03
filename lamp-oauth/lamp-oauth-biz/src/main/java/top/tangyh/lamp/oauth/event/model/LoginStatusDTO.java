package top.tangyh.lamp.oauth.event.model;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.tangyh.basic.log.util.AddressUtil;
import top.tangyh.lamp.system.enumeration.system.LoginStatusEnum;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 登录状态DTO
 *
 * @author zuihou
 * @date 2020年03月18日17:25:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Slf4j
public class LoginStatusDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;

    /**
     * 登录员工
     */
    private Long employeeId;
    /**
     * 登录用户
     */
    private Long userId;
    /**
     * 登录IP
     */
    private String requestIp;
    /**
     * 登录人姓名
     */
    private String nickName;
    /**
     * 登录人账号
     */
    private String username;
    private String mobile;
    /**
     * 登录描述
     */
    private String description;

    /**
     * 浏览器请求头
     */
    private String ua;
    /**
     * 登录地点
     */
    private String location;

    /**
     * '登录状态;[01-登录成功 02-验证码错误 03-密码错误 04-账号锁定 05-切换租户 06-短信验证码错误]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.LOGIN_STATUS)
     */

    private String status;

    public static LoginStatusDTO success(Long userId, Long employeeId) {
        return LoginStatusDTO.builder()
                .userId(userId).employeeId(employeeId)
                .status(LoginStatusEnum.SUCCESS.getCode()).description("登录成功")
                .build().setInfo();
    }

    public static LoginStatusDTO switchOrg(Long userId, Long employeeId) {
        return LoginStatusDTO.builder()
                .userId(userId).employeeId(employeeId)
                .status(LoginStatusEnum.SWITCH.getCode()).description("切换租户")
                .build().setInfo();
    }

    public static LoginStatusDTO fail(Long userId, LoginStatusEnum status, String description) {
        return LoginStatusDTO.builder()
                .userId(userId)
                .status(status.getCode()).description(description)
                .build().setInfo();
    }

    public static LoginStatusDTO fail(String username, LoginStatusEnum status, String description) {
        return LoginStatusDTO.builder()
                .username(username)
                .status(status.getCode()).description(description)
                .build().setInfo();
    }

    public static LoginStatusDTO smsCodeError(String mobile, LoginStatusEnum status, String description) {
        return LoginStatusDTO.builder()
                .mobile(mobile)
                .status(status.getCode()).description(description)
                .build().setInfo();
    }

    private LoginStatusDTO setInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return this;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String tempUa = StrUtil.sub(request.getHeader("user-agent"), 0, 500);
        String tempIp = JakartaServletUtil.getClientIP(request);
        log.info("tempIp={}, ua={}", tempIp, tempUa);
        String tempLocation = isLocalHostIp(tempIp) ? "localhost" : AddressUtil.getRegion(tempIp);

        this.ua = tempUa;
        this.requestIp = tempIp;
        this.location = tempLocation;
        return this;
    }

    /**
     * 判断是否为本地IP地址的方法
     */
    private boolean isLocalHostIp(String ipAddress) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            return inetAddress.isLoopbackAddress();
        } catch (UnknownHostException e) {
            // 处理异常情况，如果无法解析IP地址，则不视为本地地址
            return false;
        }
    }

}
