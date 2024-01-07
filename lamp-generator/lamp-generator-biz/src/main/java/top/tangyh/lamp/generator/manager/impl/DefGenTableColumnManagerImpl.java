package top.tangyh.lamp.generator.manager.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.lamp.generator.entity.DefGenTableColumn;
import top.tangyh.lamp.generator.manager.DefGenTableColumnManager;
import top.tangyh.lamp.generator.mapper.DefGenTableColumnMapper;

import java.util.Collection;

/**
 * <p>
 * 通用业务实现类
 * 代码生成字段
 * </p>
 *
 * @author zuihou
 * @date 2022-03-01
 * @create [2022-03-01] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefGenTableColumnManagerImpl extends SuperManagerImpl<DefGenTableColumnMapper, DefGenTableColumn> implements DefGenTableColumnManager {
    @Override
    public boolean removeByTableIds(Collection<Long> idList) {
        if (CollUtil.isEmpty(idList)) {
            return false;
        }
        return remove(Wrappers.<DefGenTableColumn>lambdaQuery().in(
                DefGenTableColumn::getTableId, idList
        ));
    }
}
