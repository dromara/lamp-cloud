package top.tangyh.lamp.generator.converts;

import top.tangyh.lamp.generator.config.DateType;
import top.tangyh.lamp.generator.rules.ColumnType;
import top.tangyh.lamp.generator.rules.DbColumnType;

import static top.tangyh.lamp.generator.converts.TypeConverts.contains;
import static top.tangyh.lamp.generator.converts.TypeConverts.containsAny;
import static top.tangyh.lamp.generator.rules.DbColumnType.BLOB;
import static top.tangyh.lamp.generator.rules.DbColumnType.BYTE_ARRAY;
import static top.tangyh.lamp.generator.rules.DbColumnType.FLOAT;
import static top.tangyh.lamp.generator.rules.DbColumnType.STRING;

/**
 * Oracle 数据库生成对应实体类时字段类型转换，跟据 Oracle 中的数据类型，返回对应的 Java 类型
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/8/12 11:18 AM
 * @create [2022/8/12 11:18 AM ] [tangyh] [初始创建]
 */
public class OracleTypeConvert implements ITypeConvert {
    public static final OracleTypeConvert INSTANCE = new OracleTypeConvert();

    /**
     * 将对应的类型名称转换为对应的 java 类类型
     * <p>
     * String.valueOf(Integer.MAX_VALUE).length() == 10
     * Integer 不一定能装下 10 位的数字
     * <p>
     * String.valueOf(Long.MAX_VALUE).length() == 19
     * Long 不一定能装下 19 位的数字
     *
     * @param typeName 类型名称
     * @return 返回列类型
     */
    private static ColumnType toNumberType(String typeName, Long size, Integer digit) {
        if (size == null) {
            return DbColumnType.LONG;
        }
        if (digit != null && digit > 0) {
            return DbColumnType.BIG_DECIMAL;
        }
        if (Long.valueOf(1).equals(size)) {
            return DbColumnType.BOOLEAN;
        } else if (Long.valueOf(0).equals(size) || (size >= 2 && size <= 10)) {
            return DbColumnType.INTEGER;
        } else if (size > 10 && size <= 19) {
            return DbColumnType.LONG;
        }
        return DbColumnType.BIG_DECIMAL;
    }

    /**
     * 当前时间为字段类型，根据全局配置返回对应的时间类型
     *
     * @param dt   日期类型
     * @param type 类型
     * @return 返回对应的列类型
     */
    protected static ColumnType toDateType(DateType dt, String type) {
        return switch (dt) {
            case ONLY_DATE -> DbColumnType.DATE;
            case SQL_PACK -> DbColumnType.TIMESTAMP;
            case TIME_PACK -> DbColumnType.LOCAL_DATE_TIME;
        };
    }

    @Override
    public ColumnType processTypeConvert(DateType datetype, String fieldType, Long size, Integer digit) {
        return TypeConverts.use(fieldType)
                .test(containsAny("char", "clob").then(STRING))
                .test(containsAny("date", "timestamp").then(p -> toDateType(datetype, p)))
                .test(contains("number").then(p -> toNumberType(p, size, digit)))
                .test(contains("float").then(FLOAT))
                .test(contains("blob").then(BLOB))
                .test(containsAny("binary", "raw").then(BYTE_ARRAY))
                .or(STRING);
    }

}
