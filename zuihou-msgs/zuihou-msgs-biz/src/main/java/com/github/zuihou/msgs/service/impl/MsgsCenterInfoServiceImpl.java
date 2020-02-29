package com.github.zuihou.msgs.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.msgs.dao.MsgsCenterInfoMapper;
import com.github.zuihou.msgs.dto.MsgsCenterInfoPageResultDTO;
import com.github.zuihou.msgs.dto.MsgsCenterInfoQueryDTO;
import com.github.zuihou.msgs.dto.MsgsCenterInfoSaveDTO;
import com.github.zuihou.msgs.entity.MsgsCenterInfo;
import com.github.zuihou.msgs.entity.MsgsCenterInfoReceive;
import com.github.zuihou.msgs.service.MsgsCenterInfoReceiveService;
import com.github.zuihou.msgs.service.MsgsCenterInfoService;
import com.github.zuihou.utils.BeanPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.zuihou.utils.StrHelper.getOrDef;

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
public class MsgsCenterInfoServiceImpl extends ServiceImpl<MsgsCenterInfoMapper, MsgsCenterInfo> implements MsgsCenterInfoService {
    @Autowired
    private MsgsCenterInfoReceiveService msgsCenterInfoReceiveService;

    @Override
    public IPage<MsgsCenterInfoPageResultDTO> page(IPage<MsgsCenterInfoPageResultDTO> page, MsgsCenterInfoQueryDTO data) {
        return baseMapper.page(page, data);
    }

    @Override
    public MsgsCenterInfo saveMsgs(MsgsCenterInfoSaveDTO data) {
        MsgsCenterInfo info = BeanPlusUtil.toBean(data.getMsgsCenterInfoDTO(), MsgsCenterInfo.class);
        info.setTitle(getOrDef(info.getTitle(), info.getContent()));
        info.setAuthor(getOrDef(info.getAuthor(), BaseContextHandler.getName()));
        super.save(info);

        //公式公告，不会指定接收人
        Set<Long> userIdList = data.getUserIdList();
        if (CollectionUtil.isNotEmpty(userIdList)) {
            List<MsgsCenterInfoReceive> receiveList = userIdList.stream().map((userId) -> MsgsCenterInfoReceive.builder()
                    .isRead(false)
                    .userId(userId)
                    .msgsCenterId(info.getId())
                    .build()).collect(Collectors.toList());
            msgsCenterInfoReceiveService.saveBatch(receiveList);
        }
        return info;
    }

    @Override
    public boolean delete(Long[] ids, Long userId) {
        msgsCenterInfoReceiveService.remove(Wraps.<MsgsCenterInfoReceive>lbQ()
                .eq(MsgsCenterInfoReceive::getUserId, userId)
                .in(MsgsCenterInfoReceive::getMsgsCenterId, ids));

        for (Long msgCenterId : ids) {
            int count = msgsCenterInfoReceiveService.count(Wraps.<MsgsCenterInfoReceive>lbQ()
                    .eq(MsgsCenterInfoReceive::getMsgsCenterId, msgCenterId));
            log.info("count={}", count);
            if (count <= 0) {
                super.remove(Wraps.<MsgsCenterInfo>lbQ().eq(MsgsCenterInfo::getId, msgCenterId));
            }
        }
        return true;
    }

    /**
     * 修改状态
     * 公告的已读，新增记录
     * <p>
     * 其他的更新状态
     *
     * @param msgCenterIds 主表id
     * @param userId       用户id
     * @return
     */
    @Override
    public boolean mark(List<Long> msgCenterIds, Long userId) {
        if (CollectionUtil.isEmpty(msgCenterIds) || userId == null) {
            return true;
        }
        List<MsgsCenterInfo> list = (List<MsgsCenterInfo>) super.listByIds(msgCenterIds);

        //其他类型的修改状态
        if (!list.isEmpty()) {
            List<Long> idList = list.stream().mapToLong(MsgsCenterInfo::getId).boxed().collect(Collectors.toList());
            msgsCenterInfoReceiveService.update(Wraps.<MsgsCenterInfoReceive>lbU()
                    .eq(MsgsCenterInfoReceive::getUserId, userId)
                    .in(MsgsCenterInfoReceive::getMsgsCenterId, idList)
                    .set(MsgsCenterInfoReceive::getIsRead, true)
                    .set(MsgsCenterInfoReceive::getUpdateTime, LocalDateTime.now())
                    .set(MsgsCenterInfoReceive::getUpdateUser, userId)
            );
        }
        return true;
    }

//    private boolean filter(MsgsCenterInfo info, Long userId, List<MsgsCenterInfoReceive> receiveList) {
//        if (receiveList.isEmpty()) {
//            return true;
//        }
//        return receiveList.stream().anyMatch((receive) ->
//                !(receive.getMsgsCenterId().equals(info.getId()) && receive.getUserId().equals(userId))
//        );
//    }
}
