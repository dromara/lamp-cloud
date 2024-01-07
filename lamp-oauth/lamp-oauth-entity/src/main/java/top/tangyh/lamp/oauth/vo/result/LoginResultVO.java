package top.tangyh.lamp.oauth.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

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
@Schema(description = "登录结果")
public class LoginResultVO {

    @Schema(description = "随机数")
    private String uuid;

    /**
     * token
     */
    @Schema(description = "token")
    private String token;
    @Schema(description = "刷新token")
    private String refreshToken;
    /**
     * Token 有效时间：单位：秒
     */
    @Schema(description = "有效期")
    private Long expire;
    /**
     * Token 到期时间
     */
    @Schema(description = "到期时间")
    private LocalDateTime expiration;
}
