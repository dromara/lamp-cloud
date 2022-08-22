package top.tangyh.lamp.model.constant;

import com.baomidou.mybatisplus.annotation.SqlCondition;

/**
 * @author tangyh
 * @version 3.8.0
 * @date 2022/8/11 10:41 PM
 * @create [2022/8/11 10:41 PM ] [tangyh] [初始创建]
 */
public class Condition {

    /**  MySQL、Oracle 数据库的 模糊查询 */
//    public static final String LIKE = SqlCondition.LIKE;
    /**  ORACLE 数据库的 模糊查询 */
    public static final String LIKE = SqlCondition.ORACLE_LIKE;
}
