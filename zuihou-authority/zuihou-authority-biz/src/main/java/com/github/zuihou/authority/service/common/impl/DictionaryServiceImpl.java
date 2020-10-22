package com.github.zuihou.authority.service.common.impl;

import cn.hutool.core.util.StrUtil;
import com.github.zuihou.authority.dao.common.DictionaryMapper;
import com.github.zuihou.authority.entity.common.Dictionary;
import com.github.zuihou.authority.service.common.DictionaryService;
import com.github.zuihou.base.service.SuperServiceImpl;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.utils.BizAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 字典类型
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Service
public class DictionaryServiceImpl extends SuperServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Dictionary model) {
        int count = count(Wraps.<Dictionary>lbQ().eq(Dictionary::getType, model.getType()));
        BizAssert.isFalse(count > 0, StrUtil.format("字典类型[{}]已经存在，请勿重复创建", model.getType()));
        return super.save(model);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Dictionary model) {
        int count = count(Wraps.<Dictionary>lbQ().eq(Dictionary::getType, model.getType()).ne(Dictionary::getId, model.getId()));
        BizAssert.isFalse(count > 0, StrUtil.format("字典类型[{}]已经存在，请勿重复创建", model.getType()));
        return super.updateById(model);
    }
}
