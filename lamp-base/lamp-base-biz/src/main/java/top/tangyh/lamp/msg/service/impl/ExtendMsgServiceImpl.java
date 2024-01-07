package top.tangyh.lamp.msg.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.basic.context.ContextConstants;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.lamp.common.api.JobApi;
import top.tangyh.lamp.common.dto.XxlJobInfoVO;
import top.tangyh.lamp.model.entity.system.SysUser;
import top.tangyh.lamp.msg.entity.DefMsgTemplate;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.entity.ExtendMsgRecipient;
import top.tangyh.lamp.msg.entity.ExtendNotice;
import top.tangyh.lamp.msg.enumeration.MsgTemplateTypeEnum;
import top.tangyh.lamp.msg.enumeration.SourceType;
import top.tangyh.lamp.msg.enumeration.TaskStatus;
import top.tangyh.lamp.msg.event.MsgEventVO;
import top.tangyh.lamp.msg.event.MsgSendEvent;
import top.tangyh.lamp.msg.manager.ExtendMsgManager;
import top.tangyh.lamp.msg.manager.ExtendMsgRecipientManager;
import top.tangyh.lamp.msg.manager.ExtendNoticeManager;
import top.tangyh.lamp.msg.service.ExtendMsgService;
import top.tangyh.lamp.msg.vo.result.ExtendMsgResultVO;
import top.tangyh.lamp.msg.vo.update.ExtendMsgPublishVO;
import top.tangyh.lamp.msg.vo.update.ExtendMsgSendVO;
import top.tangyh.lamp.msg.ws.WebSocketSubject;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务实现类
 * 消息
 * </p>
 *
 * @author zuihou
 * @date 2022-07-10 11:41:17
 * @create [2022-07-10 11:41:17] [zuihou] [代码生成器生成]
 */

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExtendMsgServiceImpl extends SuperServiceImpl<ExtendMsgManager, Long, ExtendMsg> implements ExtendMsgService {
    private final ExtendMsgRecipientManager recipientManager;
    private final ExtendNoticeManager extendNoticeManager;
    private final JobApi jobApi;

    @Override
    public ExtendMsgResultVO getResultById(Long id) {
        ExtendMsg msg = superManager.getById(id);
        ExtendMsgResultVO result = BeanUtil.toBean(msg, ExtendMsgResultVO.class);
        List<ExtendMsgRecipient> list = recipientManager.listByMsgId(id);
        result.setRecipientList(list.stream().map(ExtendMsgRecipient::getRecipient).toList());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean publish(ExtendMsgPublishVO data, SysUser sysUser) {
        ExtendMsg extendMsg = BeanUtil.toBean(data, ExtendMsg.class);
        extendMsg.setType(MsgTemplateTypeEnum.NOTICE.getCode());
        extendMsg.setChannel(SourceType.APP);

        extendMsg.setCreatedOrgId(sysUser.getEmployee() != null ? sysUser.getEmployee().getLastDeptId() : null);
        if (data != null && data.getDraft() != null && data.getDraft()) {
            extendMsg.setStatus(TaskStatus.DRAFT);
        } else {
            extendMsg.setStatus(TaskStatus.WAITING);
        }
        if (extendMsg.getId() == null) {
            superManager.save(extendMsg);
        } else {
            superManager.updateById(extendMsg);
            recipientManager.remove(Wraps.<ExtendMsgRecipient>lbQ().eq(ExtendMsgRecipient::getMsgId, extendMsg.getId()));
        }
        List<ExtendMsgRecipient> recipientList = data.getRecipientList().stream().map((item) -> {
            ExtendMsgRecipient recipient = new ExtendMsgRecipient();
            recipient.setMsgId(extendMsg.getId());
            recipient.setRecipient(item);
            return recipient;
        }).toList();
        recipientManager.saveBatch(recipientList);

        if (data.getSendTime() == null) {
            List<ExtendNotice> noticeList = data.getRecipientList().stream().map((item) -> {
                ExtendNotice notice = new ExtendNotice();
                BeanUtil.copyProperties(extendMsg, notice);
                notice.setId(null);
                notice.setMsgId(extendMsg.getId());
                notice.setRecipientId(Long.valueOf(item));
                notice.setIsHandle(false);
                notice.setIsRead(false);
                notice.setHandleTime(null);
                notice.setReadTime(null);
                notice.setAutoRead(true);
                return notice;
            }).toList();
            extendNoticeManager.saveBatch(noticeList);

            data.getRecipientList().forEach(employeeId -> {
                WebSocketSubject subject = WebSocketSubject.Holder.getSubject(employeeId);
                // 通知客户端 接收消息
                subject.notify("1", null);
            });

            extendMsg.setStatus(TaskStatus.SUCCESS);
            superManager.updateById(extendMsg);
        } else {
            // 务必启动 lamp-job-pro 项目，否则调用会失败！
            Map<String, Long> param = MapUtil.builder("msgId", extendMsg.getId()).build();

            XxlJobInfoVO xxlJobInfoVO = XxlJobInfoVO.create("lamp-none-executor",
                    "【发送消息】" + extendMsg.getTitle(), extendMsg.getSendTime(), "publishMsg", JsonUtil.toJson(param));
            jobApi.addTimingTask(xxlJobInfoVO);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishNotice(Long msgId) {
        ExtendMsg extendMsg = superManager.getById(msgId);
        ArgumentAssert.notNull(extendMsg, "消息不存在");
        List<ExtendMsgRecipient> recipientList = recipientManager.listByMsgId(extendMsg.getId());
        ArgumentAssert.notEmpty(recipientList, "消息接收人为空");

        List<ExtendNotice> noticeList = recipientList.stream().map((item) -> {
            ExtendNotice notice = new ExtendNotice();
            BeanUtil.copyProperties(extendMsg, notice);
            notice.setId(null);
            notice.setMsgId(extendMsg.getId());
            notice.setRecipientId(Long.valueOf(item.getRecipient()));
            notice.setIsHandle(false);
            notice.setIsRead(false);
            notice.setHandleTime(null);
            notice.setReadTime(null);
            notice.setAutoRead(true);
            return notice;
        }).toList();
        extendNoticeManager.saveBatch(noticeList);

        recipientList.forEach(employeeId -> {
            WebSocketSubject subject = WebSocketSubject.Holder.getSubject(employeeId);
            // 通知客户端 接收消息
            subject.notify("1", null);
        });

        extendMsg.setStatus(TaskStatus.SUCCESS);
        superManager.updateById(extendMsg);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean send(ExtendMsgSendVO data, DefMsgTemplate msgTemplate, SysUser sysUser) {
        ExtendMsg extendMsg = BeanUtil.toBean(data, ExtendMsg.class);
        extendMsg.setChannel(SourceType.SERVICE);
        extendMsg.setType(msgTemplate.getType());
        extendMsg.setRemindMode(msgTemplate.getRemindMode());
        if (CollUtil.isNotEmpty(data.getParamList())) {
            extendMsg.setParam(JsonUtil.toJson(data.getParamList()));
        }
        extendMsg.setCreatedOrgId((sysUser != null && sysUser.getEmployee() != null) ? sysUser.getEmployee().getLastDeptId() : null);

        extendMsg.setStatus(TaskStatus.WAITING);
        if (extendMsg.getId() == null) {
            superManager.save(extendMsg);
        } else {
            superManager.updateById(extendMsg);
            recipientManager.remove(Wraps.<ExtendMsgRecipient>lbQ().eq(ExtendMsgRecipient::getMsgId, extendMsg.getId()));
        }

        List<ExtendMsgRecipient> recipientList = data.getRecipientList().stream().map((item) -> {
            ExtendMsgRecipient recipient = new ExtendMsgRecipient();
            recipient.setMsgId(extendMsg.getId());
            recipient.setRecipient(item.getRecipient());
            recipient.setExt(item.getExt());
            return recipient;
        }).toList();
        recipientManager.saveBatch(recipientList);

        //3, 判断是否立即发送
        if (data.getSendTime() == null) {
            MsgEventVO msgEventVO = new MsgEventVO();
            msgEventVO.setMsgId(extendMsg.getId()).copy();
            SpringUtils.publishEvent(new MsgSendEvent(msgEventVO));
        } else {
            // 务必启动 lamp-job-pro 项目，否则调用会失败！
            Map<String, Long> param = MapUtil.builder("msgId", extendMsg.getId()).build();

            XxlJobInfoVO xxlJobInfoVO = XxlJobInfoVO.create("lamp-none-executor",
                    "【发送消息】" + extendMsg.getTitle(), extendMsg.getSendTime(), "sendMsg", JsonUtil.toJson(param));
            jobApi.addTimingTask(xxlJobInfoVO);
        }
        return true;
    }

}


