package top.tangyh.lamp.system.service.system.impl;

import cn.hutool.core.collection.CollUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.basic.cache.repository.CachePlusOps;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.model.cache.CacheHashKey;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.lamp.common.cache.tenant.base.DictCacheKeyBuilder;
import top.tangyh.lamp.common.constant.DefValConstants;

import top.tangyh.lamp.model.enumeration.system.DictClassifyEnum;
import top.tangyh.lamp.system.entity.system.DefDict;
import top.tangyh.lamp.system.manager.system.DefDictManager;
import top.tangyh.lamp.system.service.system.DefDictService;
import top.tangyh.lamp.system.vo.save.system.DefDictItemSaveVO;
import top.tangyh.lamp.system.vo.save.system.DefDictSaveVO;
import top.tangyh.lamp.system.vo.update.system.DefDictItemUpdateVO;
import top.tangyh.lamp.system.vo.update.system.DefDictUpdateVO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 业务实现类
 * 字典
 * </p>
 *
 * @author zuihou
 * @date 2021-10-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefDictServiceImpl extends SuperServiceImpl<DefDictManager, Long, DefDict> implements DefDictService {

    private final CachePlusOps cachePlusOps;

    @Override
    public boolean checkByKey(String key, Long id) {
        ArgumentAssert.notEmpty(key, "请填写字典标识");
        return superManager.count(Wraps.<DefDict>lbQ().eq(DefDict::getKey, key)
                .eq(DefDict::getParentId, DefValConstants.PARENT_ID).ne(DefDict::getId, id)) > 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public <SaveVO> DefDict save(SaveVO saveVO) {
        DefDictSaveVO dictSaveVO = (DefDictSaveVO) saveVO;
        ArgumentAssert.isFalse(checkByKey(dictSaveVO.getKey(), null), "字典标识已存在");
        DefDict dict = BeanPlusUtil.toBean(dictSaveVO, DefDict.class);
        dict.setClassify(DictClassifyEnum.SYSTEM.getCode());
        dict.setParentId(DefValConstants.PARENT_ID);
        superManager.save(dict);

        saveItem(dictSaveVO.getInsertList(), dict);
        return dict;
    }

    private void saveItem(List<DefDictItemSaveVO> insertList, DefDict dict) {
        if (CollUtil.isNotEmpty(insertList)) {
            List<DefDict> itemList = new ArrayList<>();
            insertList.forEach(insert -> {
                DefDict item = new DefDict();
                BeanPlusUtil.copyProperties(insert, item);
                item.setParentId(dict.getId());
                item.setParentKey(dict.getKey());
                item.setClassify(DictClassifyEnum.SYSTEM.getCode());
                itemList.add(item);

                CacheHashKey hashKey = DictCacheKeyBuilder.builder(item.getParentKey(), item.getKey());
                cachePlusOps.hSet(hashKey, item.getName());
            });
            superManager.saveBatch(itemList);
        }
    }

    private void updateItem(List<DefDictItemUpdateVO> updateInsert, DefDict dict, DefDict old) {
        if (CollUtil.isNotEmpty(updateInsert)) {
            List<DefDict> itemList = new ArrayList<>();
            updateInsert.forEach(insert -> {
                DefDict item = new DefDict();
                BeanPlusUtil.copyProperties(insert, item);
                item.setParentId(dict.getId());
                item.setParentKey(dict.getKey());
                item.setClassify(DictClassifyEnum.SYSTEM.getCode());
                itemList.add(item);

                // 淘汰旧缓存
                CacheHashKey oldHashKey = DictCacheKeyBuilder.builder(item.getParentKey(), old.getKey());
                cachePlusOps.hDel(oldHashKey);
                // 设置新缓存
                CacheHashKey hashKey = DictCacheKeyBuilder.builder(item.getParentKey(), item.getKey());
                cachePlusOps.hSet(hashKey, item.getName());
            });
            superManager.updateBatchById(itemList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteDict(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return false;
        }
        List<DefDict> list = superManager.listByIds(ids);
        if (CollUtil.isEmpty(list)) {
            return false;
        }
        // 删除 字典条目
        superManager.remove(Wraps.<DefDict>lbQ().in(DefDict::getParentId, ids));

//        删除字典
        boolean flag = removeByIds(ids);
        CacheHashKey[] typeKeys = list.stream().map(type -> DictCacheKeyBuilder.builder(type.getKey())).toArray(CacheHashKey[]::new);
        cachePlusOps.del(typeKeys);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <UpdateVO> DefDict updateById(UpdateVO updateVO) {
        DefDictUpdateVO dictUpdateVO = (DefDictUpdateVO) updateVO;
        ArgumentAssert.isFalse(checkByKey(dictUpdateVO.getKey(), dictUpdateVO.getId()), "标识【{}】重复", dictUpdateVO.getKey());

        DefDict old = getById(dictUpdateVO.getId());

        DefDict dict = BeanPlusUtil.toBean(dictUpdateVO, DefDict.class);
        dict.setParentId(DefValConstants.PARENT_ID);
        dict.setClassify(DictClassifyEnum.SYSTEM.getCode());
        superManager.updateById(dict);

        saveItem(dictUpdateVO.getInsertList(), dict);
        updateItem(dictUpdateVO.getUpdateList(), dict, old);
        superManager.removeItemByIds(dictUpdateVO.getDeleteList());

        return dict;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public DefDict copy(Long id) {
        DefDict old = getById(id);
        ArgumentAssert.notNull(old, "字典不存在或已被删除，请刷新重试");

        DefDict dict = BeanPlusUtil.toBean(old, DefDict.class);
        dict.setId(null);
        dict.setKey(dict.getKey() + "_copy");
        dict.setParentId(DefValConstants.PARENT_ID);
        dict.setClassify(DictClassifyEnum.SYSTEM.getCode());
        superManager.save(dict);

        LbQueryWrap<DefDict> wrap = Wraps.<DefDict>lbQ().eq(DefDict::getParentId, old.getId());
        List<DefDict> itemList = superManager.list(wrap);
        itemList.forEach(item -> {
            item.setId(null);
            item.setParentId(dict.getId());
            item.setParentKey(dict.getKey());
        });
        superManager.saveBatch(itemList);
        return dict;
    }


    @Override
    public List<DefDict> findItemByDictId(Long id) {
        return list(Wraps.<DefDict>lbQ().eq(DefDict::getParentId, id));
    }
}
