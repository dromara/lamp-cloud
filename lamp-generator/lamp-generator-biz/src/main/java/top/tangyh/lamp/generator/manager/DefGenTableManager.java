package top.tangyh.lamp.generator.manager;

import com.baomidou.mybatisplus.annotation.DbType;
import top.tangyh.basic.base.manager.SuperManager;
import top.tangyh.lamp.generator.entity.DefGenTable;

import javax.sql.DataSource;

/**
 * <p>
 * 通用业务接口
 * 代码生成
 * </p>
 *
 * @author zuihou
 * @date 2022-03-01
 */
public interface DefGenTableManager extends SuperManager<DefGenTable> {
    /**
     * 获取数据源
     *
     * @param dsId dsId
     * @return javax.sql.DataSource
     * @author tangyh
     * @date 2022/3/26 11:25 AM
     * @create [2022/3/26 11:25 AM ] [tangyh] [初始创建]
     * @update [2022/3/26 11:25 AM ] [tangyh] [变更描述]
     */
    DataSource getDs(Long dsId);

    /**
     * 获取数据库类型
     *
     * @return com.baomidou.mybatisplus.annotation.DbType
     * @author tangyh
     * @date 2022/8/17 9:02 PM
     * @create [2022/8/17 9:02 PM ] [tangyh] [初始创建]
     */
    DbType getDbType();
}
