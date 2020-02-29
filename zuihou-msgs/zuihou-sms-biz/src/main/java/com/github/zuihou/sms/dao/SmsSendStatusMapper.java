package com.github.zuihou.sms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuihou.sms.entity.SmsSendStatus;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 短信发送状态
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Repository
public interface SmsSendStatusMapper extends BaseMapper<SmsSendStatus> {

}
