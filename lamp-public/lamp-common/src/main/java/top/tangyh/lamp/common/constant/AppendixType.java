package top.tangyh.lamp.common.constant;

import top.tangyh.basic.utils.ArgumentAssert;

import java.util.ArrayList;
import java.util.List;

/**
 * 附件表中事先约定的业务类型。
 * <p>
 * 预定方式为：  定义一个系统级别的接口   此接口内包含这个系统内的所有表中需要的附件类型
 * key定时方式为entity_field
 * value定义方式为： 系统简称-entity-field
 *
 * @author zuihou
 * @date 2018/12/11
 */
public final class AppendixType {

    /**
     * 后端代码不需要使用该 业务类型时， 无需使用常量，直接在ALL_TYPES数组中写字符串即可。
     * 命名规则:
     * 业务表名_字段名
     */
    public static final List<String> ALL_TYPES = new ArrayList<>();


    private AppendixType() {
    }

    public static void assertType(String type) {
        ArgumentAssert.contain(ALL_TYPES, type, "附件类型未定义");
    }

    public interface System {
        /**
         * 默认库 应用logo
         */
        String DEF__APPLICATION__LOGO = "DEF__APPLICATION__LOGO";

        /**
         * 默认库 用户头像
         *
         * @author tangyh
         * @date 2021/8/14 8:47 下午
         * @create [2021/8/14 8:47 下午 ] [tangyh] [初始创建]
         */
        String DEF__USER__AVATAR = "DEF__USER__AVATAR";
    }

    /**
     * 权限管理系统中有关的的附件类型定义
     *
     * @author zuihou
     */

    public interface Base {


    }

    /**
     * 文件 业务类型定义
     */
    interface File {
        /**
         * 基础库 文件中心
         *
         * @author tangyh
         * @date 2021/8/14 8:45 下午
         * @create [2021/8/14 8:45 下午 ] [tangyh] [初始创建]
         */
        String BASE__FILE = "BASE__FILE";
    }


    /**
     * 消息系统 业务类型定义
     */
    interface Msg {
        /**
         * 扩展库 消息内容附件
         *
         * @author tangyh
         * @date 2021/8/14 8:47 下午
         * @create [2021/8/14 8:47 下午 ] [tangyh] [初始创建]
         */
        String EXTEND__MSG__CONTENT = "EXTEND__MSG__CONTENT";
    }


}
