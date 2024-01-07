package top.tangyh.lamp.msg.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.model.entity.system.SysUser;
import top.tangyh.lamp.msg.entity.DefInterface;
import top.tangyh.lamp.msg.entity.DefMsgTemplate;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.entity.ExtendMsgRecipient;
import top.tangyh.lamp.msg.service.DefInterfacePropertyService;
import top.tangyh.lamp.msg.service.DefInterfaceService;
import top.tangyh.lamp.msg.service.DefMsgTemplateService;
import top.tangyh.lamp.msg.service.ExtendMsgRecipientService;
import top.tangyh.lamp.msg.service.ExtendMsgService;
import top.tangyh.lamp.msg.strategy.MsgContext;
import top.tangyh.lamp.msg.vo.update.ExtendMsgPublishVO;
import top.tangyh.lamp.msg.vo.update.ExtendMsgSendVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static top.tangyh.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;

/**
 * 消息业务层，
 * <p>
 * 目的，跨库操作查询（千万不能加 @Transactional,否则跨库失效)
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/7/18 10:36 PM
 * @create [2022/7/18 10:36 PM ] [tangyh] [初始创建]
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MsgBiz {

    private final ExtendMsgService extendMsgService;
    private final DefMsgTemplateService extendMsgTemplateService;
    private final ExtendMsgRecipientService extendMsgRecipientService;
    private final DefInterfaceService defInterfaceService;
    private final DefInterfacePropertyService defInterfacePropertyService;
    private final MsgContext msgContext;

    /**
     * 执行消息发送
     *
     * @param id 消息ID
     * @return boolean 是否成功
     * @author tangyh
     * @date 2022/7/24 6:31 PM
     * @create [2022/7/24 6:31 PM ] [tangyh] [初始创建]
     */
    public boolean execSend(Long id) {
        ExtendMsg extendMsg = extendMsgService.getById(id);
        ArgumentAssert.notNull(extendMsg, "请先保存消息");

        // 先查租户库，在查默认库
        DefMsgTemplate extendMsgTemplate = extendMsgTemplateService.getByCode(extendMsg.getTemplateCode());
        ArgumentAssert.notNull(extendMsgTemplate, "请配置消息模板");

        DefInterface defInterface = defInterfaceService.getById(extendMsgTemplate.getInterfaceId());
        ArgumentAssert.notNull(defInterface, "请配置消息接口：{}", extendMsgTemplate.getType());
        // 先查租户库，在查默认库
        Map<String, Object> propertyParams = defInterfacePropertyService.listByInterfaceId(defInterface.getId());
        List<ExtendMsgRecipient> recipientList = extendMsgRecipientService.listByMsgId(id);

        return msgContext.execSend(extendMsg, extendMsgTemplate, recipientList, defInterface, propertyParams);
    }

    /**
     * 发送消息
     *
     * @param data
     * @return
     */
    public Boolean sendByTemplate(ExtendMsgSendVO data, SysUser sysUser) {
        DefMsgTemplate msgTemplate = validAndInit(data);
        return extendMsgService.send(data, msgTemplate, sysUser);
    }


    /**
     * 验证数据，并初始化数据
     */
    private DefMsgTemplate validAndInit(ExtendMsgSendVO msgSaveVO) {
        ArgumentAssert.notEmpty(msgSaveVO.getTemplateCode(), "请选择消息模板");

        DefMsgTemplate msgTemplate = null;
        if (StrUtil.isNotEmpty(msgSaveVO.getTemplateCode())) {
            msgTemplate = extendMsgTemplateService.getByCode(msgSaveVO.getTemplateCode());
        }
        ArgumentAssert.notNull(msgTemplate, "请选择正确的消息模板");

        //1，验证必要参数
        ArgumentAssert.notEmpty(msgSaveVO.getRecipientList(), "请填写消息接收人");

        // 验证定时发送的时间，至少大于（当前时间+5分钟） ，是为了防止 定时调度或者是保存数据跟不上
        if (msgSaveVO.getSendTime() != null) {
            boolean flag = LocalDateTime.now().plusMinutes(4).isBefore(msgSaveVO.getSendTime());
            ArgumentAssert.isTrue(flag, "定时发送时间至少在当前时间的5分钟之后");
        }

        if (CollUtil.isEmpty(msgSaveVO.getRecipientList())) {
            throw new BizException(BASE_VALID_PARAM.getCode(), "接收人不能为空");
        }

        return msgTemplate;
    }

    /**
     * 发布消息
     *
     * @param data    data
     * @param sysUser sysUser
     * @return java.lang.Boolean
     * @author tangyh
     * @date 2022/7/24 6:32 PM
     * @create [2022/7/24 6:32 PM ] [tangyh] [初始创建]
     */
    public Boolean publish(ExtendMsgPublishVO data, SysUser sysUser) {
        //1，验证必要参数
        ArgumentAssert.notEmpty(data.getRecipientList(), "请填写消息接收人");
        ArgumentAssert.notEmpty(data.getTitle(), "请填写标题");
        ArgumentAssert.notEmpty(data.getContent(), "请填写内容");

        // 验证定时发送的时间，至少大于（当前时间+5分钟） ，是为了防止 定时调度或者是保存数据跟不上
        if (data.getSendTime() != null) {
            boolean flag = LocalDateTime.now().plusMinutes(4).isBefore(data.getSendTime());
            ArgumentAssert.isTrue(flag, "定时发送时间至少在当前时间的5分钟之后");
        }

        if (CollUtil.isEmpty(data.getRecipientList())) {
            throw new BizException(BASE_VALID_PARAM.getCode(), "接收人不能为空");
        }

        if (data.getContent().length() > 2147483647) {
            throw new BizException(BASE_VALID_PARAM.getCode(), "发送内容不能超过2147483647字");
        }

        return extendMsgService.publish(data, sysUser);
    }
}
