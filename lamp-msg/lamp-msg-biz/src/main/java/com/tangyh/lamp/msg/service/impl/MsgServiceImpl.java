package com.tangyh.lamp.msg.service.impl;

import cn.hutool.core.collection.CollectionUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.base.service.SuperServiceImpl;
import com.tangyh.basic.context.ContextUtil;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.utils.BeanPlusUtil;
import com.tangyh.lamp.msg.dao.MsgMapper;
import com.tangyh.lamp.msg.dto.MsgPageResult;
import com.tangyh.lamp.msg.dto.MsgQuery;
import com.tangyh.lamp.msg.dto.MsgSaveDTO;
import com.tangyh.lamp.msg.entity.Msg;
import com.tangyh.lamp.msg.entity.MsgReceive;
import com.tangyh.lamp.msg.service.MsgReceiveService;
import com.tangyh.lamp.msg.service.MsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.tangyh.basic.utils.StrHelper.getOrDef;

/**
 * <p>
 * 业务实现类
 * 消息中心
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class MsgServiceImpl extends SuperServiceImpl<MsgMapper, Msg> implements MsgService {
    private final MsgReceiveService msgReceiveService;

    @Override
    public IPage<MsgPageResult> page(IPage<MsgPageResult> page, PageParams<MsgQuery> param) {
        return baseMapper.page(page, param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Msg saveMsg(MsgSaveDTO data) {
        Msg info = BeanPlusUtil.toBean(data.getMsgDTO(), Msg.class);
        info.setTitle(getOrDef(info.getTitle(), info.getContent()));
        info.setAuthor(getOrDef(info.getAuthor(), ContextUtil.getName()));
        super.save(info);

        //公式公告，不会指定接收人
        Set<Long> userIdList = data.getUserIdList();
        if (CollectionUtil.isNotEmpty(userIdList)) {
            List<MsgReceive> receiveList = userIdList.stream().filter(userId -> userId != null && !userId.equals(-1L)).map(userId -> MsgReceive.builder()
                    .isRead(false)
                    .userId(userId)
                    .msgId(info.getId())
                    .build()).collect(Collectors.toList());
            msgReceiveService.saveBatch(receiveList);
        }
        return info;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(List<Long> ids, Long userId) {
        boolean bool = msgReceiveService.remove(Wraps.<MsgReceive>lbQ()
                .eq(MsgReceive::getUserId, userId)
                .in(MsgReceive::getMsgId, ids));

        for (Long msgCenterId : ids) {
            int count = msgReceiveService.count(Wraps.<MsgReceive>lbQ()
                    .eq(MsgReceive::getMsgId, msgCenterId));
            log.info("count={}", count);
            if (count <= 0) {
                super.remove(Wraps.<Msg>lbQ().eq(Msg::getId, msgCenterId));
            }
        }
        return bool;
    }

    /**
     * 修改状态
     * 公告的已读，新增记录
     * <p>
     * 其他的更新状态
     *
     * @param msgCenterIds 主表id
     * @param userId       用户id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean mark(List<Long> msgCenterIds, Long userId) {
        if (CollectionUtil.isEmpty(msgCenterIds) || userId == null) {
            return true;
        }
        List<Msg> list = super.listByIds(msgCenterIds);

        //其他类型的修改状态
        if (!list.isEmpty()) {
            List<Long> idList = list.stream().mapToLong(Msg::getId).boxed().collect(Collectors.toList());
            return msgReceiveService.update(Wraps.<MsgReceive>lbU()
                    .eq(MsgReceive::getUserId, userId)
                    .in(MsgReceive::getMsgId, idList)
                    .set(MsgReceive::getIsRead, true)
                    .set(MsgReceive::getUpdateTime, LocalDateTime.now())
                    .set(MsgReceive::getUpdatedBy, userId)
            );
        }
        return true;
    }

}
