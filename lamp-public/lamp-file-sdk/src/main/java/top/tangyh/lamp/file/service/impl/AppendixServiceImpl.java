package top.tangyh.lamp.file.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.basic.base.service.SuperServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbqWrapper;
import top.tangyh.basic.model.EchoVO;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.lamp.common.vo.result.AppendixResultVO;
import top.tangyh.lamp.common.vo.save.AppendixSaveVO;
import top.tangyh.lamp.file.entity.Appendix;
import top.tangyh.lamp.file.mapper.AppendixMapper;
import top.tangyh.lamp.file.service.AppendixService;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 业务附件
 * </p>
 *
 * @author tangyh
 * @date 2021-06-30
 * @create [2021-06-30] [tangyh] [初始创建]
 */
@Slf4j
@Service

@Transactional(readOnly = true)
public class AppendixServiceImpl extends SuperServiceImpl<AppendixMapper, Appendix> implements AppendixService {


    @Override
    @Transactional(readOnly = true)
    public <T extends SuperEntity<Long> & EchoVO> void echoAppendix(IPage<T> page, String... bizTypes) {
        if (page == null) {
            return;
        }
        echoAppendix(page.getRecords(), bizTypes);
    }

    @Override
    public <T extends SuperEntity<Long> & EchoVO> void echoAppendix(List<T> list, String... bizTypes) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        List<Long> ids = list.stream().map(SuperEntity::getId).collect(Collectors.toList());

        Multimap<AppendixService.AppendixBizKey, AppendixResultVO> map = listByBizIds(ids, bizTypes);

        Set<String> bizTypeSet = CollUtil.newHashSet();
        map.forEach((biz, item) -> bizTypeSet.add(biz.getBizType()));

        list.forEach(item ->
                bizTypeSet.forEach(bizType ->
                        item.getEchoMap().put(bizType, map.get(buildBiz(item.getId(), bizType)))
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Multimap<AppendixBizKey, AppendixResultVO> listByBizId(Long bizId, String... bizType) {
        ArgumentAssert.notNull(bizId, "请传入业务id");
        LbqWrapper<Appendix> wrap = Wraps.<Appendix>lbQ().eq(Appendix::getBizId, bizId).in(Appendix::getBizType, bizType);
        List<Appendix> list = list(wrap);
        return CollHelper.iterableToMultiMap(list,
                item -> AppendixBizKey.builder().bizId(item.getBizId()).bizType(item.getBizType()).build(),
                item -> BeanPlusUtil.toBean(item, AppendixResultVO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Multimap<AppendixBizKey, AppendixResultVO> listByBizIds(List<Long> bizIds, String... bizType) {
        ArgumentAssert.notEmpty(bizIds, "请传入业务id");
        LbqWrapper<Appendix> wrap = Wraps.<Appendix>lbQ().in(Appendix::getBizId, bizIds).in(Appendix::getBizType, bizType);
        List<Appendix> list = list(wrap);
        return CollHelper.iterableToMultiMap(list,
                item -> AppendixBizKey.builder().bizId(item.getBizId()).bizType(item.getBizType()).build(),
                item -> BeanPlusUtil.toBean(item, AppendixResultVO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppendixResultVO> listByBizIdAndBizType(Long bizId, String bizType) {
        ArgumentAssert.notNull(bizId, "请传入业务id");
        LbqWrapper<Appendix> wrap = Wraps.<Appendix>lbQ().eq(Appendix::getBizId, bizId)
                .eq(Appendix::getBizType, bizType);
        return BeanPlusUtil.toBeanList(list(wrap), AppendixResultVO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public AppendixResultVO getByBiz(Long bizId, String bizType) {
        ArgumentAssert.notNull(bizId, "请传入业务id");
        ArgumentAssert.notEmpty(bizType, "请传入功能点");
        LbqWrapper<Appendix> wrap = Wraps.<Appendix>lbQ().eq(Appendix::getBizId, bizId)
                .eq(Appendix::getBizType, bizType);
        List<Appendix> list = list(wrap);
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        return BeanPlusUtil.toBean(list.get(0), AppendixResultVO.class);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(Long bizId, AppendixSaveVO saveVO) {
        if (bizId != null) {
            remove(Wraps.<Appendix>lbQ().eq(Appendix::getBizId, bizId));
        }
        if (saveVO == null) {
            return true;
        }
        Appendix commonFile = BeanPlusUtil.toBean(saveVO, Appendix.class);
        commonFile.setBizId(bizId);
        save(commonFile);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(Long bizId, List<AppendixSaveVO> list) {
        if (bizId != null) {
            remove(Wraps.<Appendix>lbQ().eq(Appendix::getBizId, bizId));
        }
        if (CollUtil.isEmpty(list)) {
            return false;
        }
        List<Appendix> commonFiles = BeanPlusUtil.toBeanList(list, Appendix.class);
        commonFiles.forEach(item -> item.setBizId(bizId));
        saveBatch(commonFiles);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(Long bizId, String bizType, List<AppendixSaveVO> list) {
        removeByBizId(bizId, bizType);
        if (CollUtil.isEmpty(list)) {
            return false;
        }
        List<Appendix> commonFiles = BeanPlusUtil.toBeanList(list, Appendix.class);
        commonFiles.forEach(item -> {
            item.setBizId(bizId);
            item.setBizType(bizType);
        });
        saveBatch(commonFiles);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByBizId(List<Long> objectIds) {
        if (CollUtil.isEmpty(objectIds)) {
            return false;
        }
        return remove(Wraps.<Appendix>lbQ().in(Appendix::getBizId, objectIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByBizId(Long... objectIds) {
        if (ArrayUtil.isEmpty(objectIds)) {
            return false;
        }
        return remove(Wraps.<Appendix>lbQ().in(Appendix::getBizId, objectIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByBizId(Long bizId, String bizType) {
        ArgumentAssert.isFalse(bizId == null && StrUtil.isEmpty(bizType), "请传入对象id或功能点");
        return remove(Wraps.<Appendix>lbQ().eq(Appendix::getBizId, bizId).eq(Appendix::getBizType, bizType));
    }

}
