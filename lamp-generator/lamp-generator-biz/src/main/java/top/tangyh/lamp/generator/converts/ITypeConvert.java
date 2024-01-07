package top.tangyh.lamp.generator.converts;

import top.tangyh.lamp.generator.config.DateType;
import top.tangyh.lamp.generator.rules.ColumnType;

/**
 * 数据库字段类型转换
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/8/12 11:19 AM
 * @create [2022/8/12 11:19 AM ] [tangyh] [初始创建]
 */
public interface ITypeConvert {


    /**
     * 执行类型转换
     *
     * @param datetype  日期类型
     * @param fieldType 字段类型
     * @param digit     小数位
     * @param size      size
     * @return ignore
     */
    ColumnType processTypeConvert(DateType datetype, String fieldType, Long size, Integer digit);

}
