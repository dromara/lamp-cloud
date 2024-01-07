package top.tangyh.lamp.msg.strategy.domain.sms;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.msg.strategy.domain.BaseProperty;

/**
 * https://help.aliyun.com/document_detail/419273.htm?spm=a2c4g.11186623.0.0.3437bb6eTMh5Il
 *
 * @author zuihou
 * @date 2022/7/10 0010 18:56
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AliSmsProperty extends BaseProperty {
    /**
     * 更多服务接入点请参考： https://help.aliyun.com/document_detail/419270.html
     */
    private final static String DEF_END_POINT = "dysmsapi.aliyuncs.com";
    private final static String DEF_REGION_ID = "cn-hangzhou";

    /**
     * 地域ID
     */
    private String regionId;
    /**
     * 公网接入地址
     */
    private String endpoint;
    /**
     * 发送账号安全认证的Access Key ID
     */
    private String accessKeyId;
    /**
     * 发送账号安全认证的Secret Access Key
     */
    private String accessKeySecret;

    @Override
    public boolean initAndValid() {
        super.initAndValid();
        if (StrUtil.isEmpty(endpoint)) {
            endpoint = DEF_END_POINT;
        }
        if (StrUtil.isEmpty(regionId)) {
            regionId = DEF_REGION_ID;
        }
        ArgumentAssert.notEmpty(this.accessKeyId, "accessKeyId 不能为空");
        ArgumentAssert.notEmpty(this.accessKeySecret, "accessKeySecret 不能为空");
        return true;
    }
}
