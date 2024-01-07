package top.tangyh.lamp.generator.converts;

import top.tangyh.lamp.generator.config.DateType;
import top.tangyh.lamp.generator.rules.ColumnType;
import top.tangyh.lamp.generator.rules.DbColumnType;

import static top.tangyh.lamp.generator.converts.TypeConverts.contains;
import static top.tangyh.lamp.generator.converts.TypeConverts.containsAny;
import static top.tangyh.lamp.generator.rules.DbColumnType.BIG_DECIMAL;
import static top.tangyh.lamp.generator.rules.DbColumnType.BOOLEAN;
import static top.tangyh.lamp.generator.rules.DbColumnType.BYTE_ARRAY;
import static top.tangyh.lamp.generator.rules.DbColumnType.DOUBLE;
import static top.tangyh.lamp.generator.rules.DbColumnType.FLOAT;
import static top.tangyh.lamp.generator.rules.DbColumnType.INTEGER;
import static top.tangyh.lamp.generator.rules.DbColumnType.LONG;
import static top.tangyh.lamp.generator.rules.DbColumnType.STRING;


/**
 * SQLServer 字段类型转换
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/8/12 11:18 AM
 * @create [2022/8/12 11:18 AM ] [tangyh] [初始创建]
 */
public class SqlServerTypeConvert implements ITypeConvert {

    public static final SqlServerTypeConvert INSTANCE = new SqlServerTypeConvert();

    /**
     * 转换为日期类型
     *
     * @param dt   日期类型
     * @param type 类型
     * @return 返回对应的列类型
     */
    public static ColumnType toDateType(DateType dt, String type) {
        return switch (dt) {
            case SQL_PACK -> switch (type) {
                case "date" -> DbColumnType.DATE_SQL;
                case "time" -> DbColumnType.TIME;
                default -> DbColumnType.TIMESTAMP;
            };
            case TIME_PACK -> switch (type) {
                case "date" -> DbColumnType.LOCAL_DATE;
                case "time" -> DbColumnType.LOCAL_TIME;
                default -> DbColumnType.LOCAL_DATE_TIME;
            };
            default -> DbColumnType.DATE;
        };
    }

    @Override
    public ColumnType processTypeConvert(DateType datetype, String fieldType, Long size, Integer digit) {
        return TypeConverts.use(fieldType)
                .test(containsAny("char", "xml", "text").then(STRING))
                .test(contains("bigint").then(LONG))
                .test(contains("int").then(INTEGER))
                .test(containsAny("date", "time").then(t -> toDateType(datetype, t)))
                .test(contains("bit").then(BOOLEAN))
                .test(containsAny("decimal", "numeric").then(DOUBLE))
                .test(contains("money").then(BIG_DECIMAL))
                .test(containsAny("binary", "image").then(BYTE_ARRAY))
                .test(containsAny("float", "real").then(FLOAT))
                .or(STRING);
    }
}
