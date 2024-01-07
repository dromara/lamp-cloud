package top.tangyh.lamp.msg.strategy.domain.sms;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.msg.strategy.domain.BaseProperty;

/**
 * @author zuihou
 * @date 2022/7/10 0010 18:56
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TencentSmsProperty extends BaseProperty {
    private final static String DEF_REGION = "ap-beijing";
    private final static String DEF_END_POINT = "sms.tencentcloudapi.com";
    /** secretId */
    private String secretId;
    /** secretKey */
    private String secretKey;
    /** 地域域名 */
    private String endpoint;
    /**
     * 地域参数
     * 用来标识希望操作哪个地域的数据。接口接受的地域取值参考接口文档中输入参数公共参数 Region 的说明。注意：某些接口不需要传递该参数，接口文档中会对此特别说明，此时即使传递该参数也不会生效。
     * https://cloud.tencent.com/document/api/382/52071
     * <p>
     * https://cloud.tencent.com/document/api/382/52071#.E5.9C.B0.E5.9F.9F.E5.88.97.E8.A1.A8
     */
    private String region;
    /** 短信 SdkAppId，在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666。 */
    private String sdkAppId;


    @Override
    public boolean initAndValid() {
        super.initAndValid();
        if (endpoint == null) {
            endpoint = DEF_END_POINT;
        }
        if (StrUtil.isEmpty(region)) {
            region = DEF_REGION;
        }

        ArgumentAssert.notEmpty(this.sdkAppId, "sdkAppId 不能为空");
        ArgumentAssert.notEmpty(this.secretKey, "secretKey 不能为空");
        ArgumentAssert.notEmpty(this.secretId, "secretId 不能为空");
        return true;
    }
}
