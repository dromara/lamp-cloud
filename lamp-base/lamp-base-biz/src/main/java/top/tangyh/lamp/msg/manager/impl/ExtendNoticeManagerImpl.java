package top.tangyh.lamp.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.lamp.msg.entity.ExtendNotice;
import top.tangyh.lamp.msg.manager.ExtendNoticeManager;
import top.tangyh.lamp.msg.mapper.ExtendNoticeMapper;

/**
 * <p>
 * 通用业务实现类
 * 通知表
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [zuihou] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExtendNoticeManagerImpl extends SuperManagerImpl<ExtendNoticeMapper, ExtendNotice> implements ExtendNoticeManager {

}


