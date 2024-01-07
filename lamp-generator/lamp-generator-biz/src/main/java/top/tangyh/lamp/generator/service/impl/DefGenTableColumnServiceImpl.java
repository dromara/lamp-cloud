package top.tangyh.lamp.generator.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.BeanPlusUtil;

import top.tangyh.lamp.generator.config.GeneratorConfig;
import top.tangyh.lamp.generator.entity.DefGenTable;
import top.tangyh.lamp.generator.entity.DefGenTableColumn;
import top.tangyh.lamp.generator.manager.DefGenTableColumnManager;
import top.tangyh.lamp.generator.manager.DefGenTableManager;
import top.tangyh.lamp.generator.service.DefGenTableColumnService;
import top.tangyh.lamp.generator.utils.GenUtils;
import top.tangyh.lamp.generator.vo.query.DefGenTableColumnPageQuery;
import top.tangyh.lamp.generator.vo.result.DefGenTableColumnResultVO;

import javax.sql.DataSource;

/**
 * <p>
 * 业务实现类
 * 代码生成字段
 * </p>
 *
 * @author zuihou
 * @date 2022-03-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefGenTableColumnServiceImpl extends SuperServiceImpl<DefGenTableColumnManager, Long, DefGenTableColumn> implements DefGenTableColumnService {
    private final DefGenTableManager defGenTableManager;
    private final GeneratorConfig generatorConfig;


    @Override
    public IPage<DefGenTableColumnResultVO> pageColumn(PageParams<DefGenTableColumnPageQuery> params) {
        IPage<DefGenTableColumn> page = params.buildPage();
        DefGenTableColumnPageQuery model = params.getModel();
        DefGenTableColumn column = BeanUtil.toBean(model, DefGenTableColumn.class);
        superManager.page(page, Wraps.lbQ(column));
        return BeanPlusUtil.toBeanPage(page, DefGenTableColumnResultVO.class);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean syncField(Long tableId, Long id) {
        DefGenTable genTable = defGenTableManager.getById(tableId);
        DefGenTableColumn genTableColumn = getById(id);
        ArgumentAssert.notNull(genTable, "请先选择需要同步的表");
        ArgumentAssert.notNull(genTableColumn, "请先选择需要同步的字段");
        DataSource ds = defGenTableManager.getDs(genTable.getDsId());

        Table tableMeta = MetaUtil.getTableMeta(ds, genTable.getName());
        for (Column column : tableMeta.getColumns()) {
            if (genTableColumn.getName().equals(column.getName())) {
                DefGenTableColumn tableColumn = GenUtils.initColumnField(generatorConfig, defGenTableManager.getDbType(), genTable, column);
                if (tableColumn != null) {
                    tableColumn.setId(id);
                    tableColumn.setTableId(tableId);
                    superManager.updateById(tableColumn);
                    break;
                }
            }
        }
        return true;
    }
}
