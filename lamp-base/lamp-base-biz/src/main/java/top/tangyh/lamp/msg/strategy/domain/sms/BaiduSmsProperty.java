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
public class BaiduSmsProperty extends BaseProperty {
    private final static String DEF_END_POINT = "http://smsv3.bj.baidubce.com";
    /**
     * accessKeyId
     */
    private String accessKeyId;
    /**
     * secretKey
     */
    private String secretKey;
    /**
     * 域名
     */
    private String endPoint;

    @Override
    public boolean initAndValid() {
        super.initAndValid();
        if (StrUtil.isEmpty(endPoint)) {
            endPoint = DEF_END_POINT;
        }
        ArgumentAssert.notEmpty(accessKeyId, "accessKeyId 不能为空");
        ArgumentAssert.notEmpty(secretKey, "secretKey 不能为空");
        return true;
    }
}
